package main

import (
	common "TCPSocket/project01/common/message"
	"TCPSocket/project01/server/process"
	"TCPSocket/project01/server/utils"
	"fmt"
	"io"
	"net"
)

type Processor struct {
	Conn net.Conn
}

//根据客户端发送的消息种类不同，决定调用哪个函数来处理
func (this *Processor) serverProcessMes(msg *common.Message) (err error) {

	//看看是否能接受
	//fmt.Println("message = ", msg)

	switch msg.Type {
	case common.LoginMessageType:
		//处理登录
		login := &process.UserProcess{
			Conn: this.Conn,
		}
		err = login.ServerProcessLogin(msg)
	case common.RegisterMessageType:
		//处理注册
		login := &process.UserProcess{
			Conn: this.Conn,
		}
		err = login.ServerProcessRegister(msg)
	case common.SmsMessageType :
		smsProcess := &process.SmsProcess{}
		smsProcess.SendMessage(msg)
	default:
		fmt.Println("消息类型不存在....")
	}
	return
}

func (this *Processor) process() (err error) {
	for {
		tf := &utils.Transfer{
			Conn: this.Conn,
		}
		message, err := tf.ReadMessage()
		if err != nil {
			if err == io.EOF {
				fmt.Println("读取完毕")
			}
			return err
		}
		err = this.serverProcessMes(&message)
		if err != nil {
			return err
		}
	}
}
