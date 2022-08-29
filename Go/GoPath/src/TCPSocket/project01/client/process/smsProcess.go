package process

import (
	"TCPSocket/project01/client/utils"
	common "TCPSocket/project01/common/message"
)
import "encoding/json"

type SmsProcess struct {

}
//发消息
func (this *SmsProcess) SendMessage(content string) (err error) {
	var msg common.Message
	msg.Type = common.SmsMessageType

	var smsMessage common.SmsMessage
	smsMessage.Content = content
	smsMessage.UserId = CurUser.UserId
	smsMessage.UserStatus = CurUser.UserStatus

	data, err := json.Marshal(smsMessage)
	if err != nil {
		return err
	}

	msg.Data = string(data)
	data, err = json.Marshal(msg)
	if err != nil {
		return err
	}
	tf := &utils.Transfer{
		Conn: CurUser.Conn,
	}
	err = tf.WriteMessage(data)
	if err != nil {
		return err
	}
	return
}
