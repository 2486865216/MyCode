package process

import (
	"TCPSocket/project01/client/utils"
	common "TCPSocket/project01/common/message"
	"encoding/json"
	"fmt"
	"net"
	"os"
)

//显示登陆成功后的页面
func showMenu() {
	fmt.Println("=====登录=====")
	fmt.Println("\t1.显示在线用户")
	fmt.Println("\t2.发送消息")
	fmt.Println("\t3.消息列表")
	fmt.Println("\t4.退出")
	var key int
	fmt.Scanf("%d\n", &key)

	var content string
	smsProcess := &SmsProcess{}

	switch key {
	case 1:
		//fmt.Println("\t1.显示在线用户")
		outputOnlineUsers()
	case 2:
		fmt.Println("请输入消息")
		fmt.Scanf("%s\n", &content)
		smsProcess.SendMessage(content)
	case 3:
		fmt.Println("\t3.消息列表户")
	case 4:
		fmt.Println("\t4.退出")
		os.Exit(0)
	default:
		fmt.Println("输入错误")
	}
}

//和服务器保持通信
func serverProcess(conn net.Conn){
	tf := utils.Transfer{
		Conn: conn,
	}
	for {
		fmt.Println("客户端正在等待服务器的消息")
		message, err := tf.ReadMessage()
		if err != nil {
			return
		}
		switch message.Type {
		case common.NotifyUserStatusMessage : //有人上线
			var notifyUserStatus common.NotifyUserStatus
			json.Unmarshal([]byte(message.Data), &notifyUserStatus)
			updateUserStatus(&notifyUserStatus)
		case common.SmsMessageType : //接收消息
			outputMessage(&message)
		default :
			fmt.Println("暂时不能处理")
		}
	}
}
