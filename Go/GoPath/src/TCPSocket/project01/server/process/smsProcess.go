package process

import (
	"TCPSocket/project01/client/utils"
	common "TCPSocket/project01/common/message"
	"encoding/json"
	"fmt"
	"net"
)

type SmsProcess struct {}

//转发消息
func (this *SmsProcess) SendMessage(msg *common.Message) {

	var smsMessage common.SmsMessage

	err := json.Unmarshal([]byte(msg.Data), &msg)
	err = json.Unmarshal([]byte(msg.Data), &smsMessage)
	if err != nil {
		return
	}

	data, err := json.Marshal(msg)
	if err != nil {
		return
	}

	for id, user := range userManager.OnlineUsers {
		if id == smsMessage.UserId {
			continue
		}
		this.sendToEveryone(data, user.Conn)
	}
}

func (this *SmsProcess) sendToEveryone(data []byte, conn net.Conn) {
	tf := &utils.Transfer{
		Conn: conn,
	}
	err := tf.WriteMessage(data)
	if err != nil {
		fmt.Println("转发消息失败 = ", err)
		return 
	}
}