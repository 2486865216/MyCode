package process

import (
	"TCPSocket/project01/client/utils"
	common "TCPSocket/project01/common/message"
	"encoding/binary"
	"encoding/json"
	"fmt"
	"net"
)

type UserProcess struct {

}

// Login 登陆函数
func (this *UserProcess) Login(userId int, userPwd string) (err error) {
	conn, err := net.Dial("tcp", "127.0.0.1:8888")
	defer conn.Close()
	if err != nil {
		fmt.Println("Dial error = ", err)
		return
	}
	var msg common.Message
	msg.Type = common.LoginMessageType
	var loginMsg = common.LoginMessage{
		UserId:  userId,
		UserPwd: userPwd,
		UserName: "login",
	}
	//序列化
	data, err := json.Marshal(loginMsg)
	if err != nil {
		fmt.Println("json error = ", err)
		return
	}
	//先发送data的长度
	//整型转切片
	pckLen := uint32(len(data))
	var buffer [4]byte
	binary.BigEndian.PutUint32(buffer[0:4], pckLen)
	n, err := conn.Write(buffer[:4])
	if n != 4 || err != nil {
		fmt.Println("conn Write error = ", err)
		return
	}
	//fmt.Println("data长度发送成功")

	//发送信息
	_, err = conn.Write(data)
	if err != nil {
		fmt.Println("send data error = ", err)
		return
	}

	//处理返回的数据
	tf := utils.Transfer{
		Conn: conn,
	}
	message, err := tf.ReadMessage()
	if err != nil {
		return
	}
	var loginResMsg common.LoginResMessage
	err = json.Unmarshal([]byte(message.Data), &loginResMsg)
	if loginResMsg.Code == 200 {
		CurUser.Conn = conn
		CurUser.UserId = userId
		CurUser.UserStatus = common.UserOnline
		fmt.Println("当前在线用户")
		for _, value := range loginResMsg.UserIds {
			if value == userId {
				continue
			}
			fmt.Printf("用户id: %d\n", value)
			user := &common.User{
				UserId: value,
				UserStatus: common.UserOnline,
			}
			onlineUsers[value] = user
		}
		go serverProcess(conn)
		//fmt.Println("登录成功")
		for  {
			showMenu()
		}
	}else {
		fmt.Println(loginResMsg.Error)
	}

	return
}

//Register 注册函数
func (this *UserProcess) Register(userId int, userPwd, userName string) (err error) {
	conn, err := net.Dial("tcp", "127.0.0.1:8888")
	if err != nil {
		return err
	}
	defer conn.Close()

	var msg common.Message
	msg.Type = common.RegisterMessageType
	var registerMessage common.RegisterMessage
	registerMessage.UserId = userId
	registerMessage.UserPwd = userPwd
	registerMessage.UserName = userName
	//序列化
	data, err := json.Marshal(registerMessage)
	if err != nil {
		fmt.Println("json error = ", err)
		return
	}
	//先发送data的长度
	//整型转切片
	pckLen := uint32(len(data))
	var buffer [4]byte
	binary.BigEndian.PutUint32(buffer[0:4], pckLen)
	n, err := conn.Write(buffer[:4])
	if n != 4 || err != nil {
		fmt.Println("conn Write error = ", err)
		return
	}
	//fmt.Println("data长度发送成功")

	//发送信息
	_, err = conn.Write(data)
	if err != nil {
		fmt.Println("send data error = ", err)
		return
	}
	//处理返回的数据
	tf := utils.Transfer{
		Conn: conn,
	}
	message, err := tf.ReadMessage()
	var registerResMessage common.RegisterResMessage
	err = json.Unmarshal([]byte(message.Data), &registerResMessage)
	if err != nil {
		return err
	}
	if registerResMessage.Code == 200 {
		fmt.Println("注册成功")
	}else {
		fmt.Println(registerResMessage.Error)
	}
	return
}

