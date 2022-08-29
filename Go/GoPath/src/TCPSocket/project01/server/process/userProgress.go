package process

import (
	common "TCPSocket/project01/common/message"
	"TCPSocket/project01/server/model"
	"TCPSocket/project01/server/utils"
	"encoding/json"
	"fmt"
	"net"
)

type UserProcess struct {
	Conn net.Conn
	//表示conn是哪个用户的
	UserId int
}

//通知所有在线用户
//userId通知其它用户已上线
func (this *UserProcess) NotifyOtherOnlineUsers(userId int) {
	for id, user := range userManager.OnlineUsers {
		if id == userId {
			continue
		}
		user.NotifyMeOnlineUsers(userId)
	}
}

func (this *UserProcess) NotifyMeOnlineUsers(userId int) {
	var msg common.Message
	msg.Type = common.NotifyUserStatusMessage

	var notifyUserStatus common.NotifyUserStatus
	notifyUserStatus.UserId = userId
	notifyUserStatus.Status = common.UserOnline

	data, err := json.Marshal(notifyUserStatus)
	if err != nil {
		return
	}
	msg.Data = string(data)

	data, err = json.Marshal(msg)
	if err != nil {
		return
	}

	tf := &utils.Transfer{
		Conn: this.Conn,
	}
	err = tf.WriteMessage(data)
	if err != nil {
		fmt.Println("notify error = ", err)
		return
	}
}

//登录
func (this *UserProcess) ServerProcessLogin(msg *common.Message) (err error){
	var loginMessage common.LoginMessage
	err = json.Unmarshal([]byte(msg.Data), &loginMessage)
	if err != nil {
		fmt.Println("json error = ", err)
		return
	}

	var resMessage common.Message
	resMessage.Type = common.LoginResMessageType
	//声明返回类型
	var result common.LoginResMessage

	//redis验证
	_, err = model.MyUserDao.Login(loginMessage.UserId, loginMessage.UserPwd)
	if err != nil {
		if err == model.ERROR_USER_NOTEXISTS {
			result.Code = 500
			result.Error = "用户不存在"
			return
		}else if err == model.ERROR_USER_PWDERROR {
			result.Code = 500
			result.Error = "密码错误"
			return
		}else {
			result.Code = 500
			result.Error = "服务器内部错误"
			return
		}
	}else {
		result.Code = 200
		//放入map
		this.UserId = loginMessage.UserId
		userManager.AddOnlineUser(this)
		//通知其它用户
		this.NotifyOtherOnlineUsers(loginMessage.UserId)
		//将当前在线用户的id放入
		for index := range userManager.OnlineUsers {
			result.UserIds = append(result.UserIds, index)
		}
		fmt.Println("登录成功")
	}

	data, err := json.Marshal(result)
	if err != nil {
		fmt.Println("json err = ", err)
		return
	}

	resMessage.Data = string(data)
	//序列化resMessage
	data, err = json.Marshal(resMessage)
	if err != nil {
		fmt.Println("resMessage json error = ", err)
		return
	}
	tf := &utils.Transfer{
		Conn: this.Conn,
	}
	err = tf.WriteMessage(data)
	return
}

func (this *UserProcess) ServerProcessRegister(msg *common.Message) (err error) {
	var registerMessage common.RegisterMessage
	err = json.Unmarshal([]byte(msg.Data), &registerMessage)
	if err != nil {
		return err
	}

	var registerResMessage common.RegisterResMessage
	var resMsg common.Message
	resMsg.Type = common.RegisterResMessageType

	err = model.MyUserDao.Register(&registerMessage)
	if err != nil {
		if err == model.ERROR_USER_EXISTS {
			registerResMessage.Code	= 505
			registerResMessage.Error = model.ERROR_USER_EXISTS.Error()
		}
	}else {
		registerResMessage.Code = 200
	}
	data, err := json.Marshal(registerResMessage)
	if err != nil {
		return
	}
	tf := &utils.Transfer{
		Conn: this.Conn,
	}

	resMsg.Data = string(data)
	data, err = json.Marshal(resMsg)
	err = tf.WriteMessage(data)
	if err != nil {
		return err
	}
	return
}