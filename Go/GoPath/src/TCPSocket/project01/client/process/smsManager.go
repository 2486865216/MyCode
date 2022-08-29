package process

import (
	common "TCPSocket/project01/common/message"
	"fmt"
)
import "encoding/json"

func outputMessage(msg *common.Message) {
	var smsMessage common.SmsMessage
	err := json.Unmarshal([]byte(msg.Data), &smsMessage)
	if err != nil {
		fmt.Println("jsonUnmarshal error ", err)
		return
	}
	fmt.Println()
	fmt.Printf("用户id:%d,\t对大家说:%s\n", smsMessage.UserId, smsMessage.Content)
	fmt.Println()
}
