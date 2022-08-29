package common

const(
	LoginMessageType = "loginMessage"
	LoginResMessageType = "loginResMessage"
	RegisterMessageType = "registerMessageType"
	RegisterResMessageType = "registerResMessageType"
	NotifyUserStatusMessage = "notifyUserStatusMessage"
	SmsMessageType = "smsMessageType"
)

//表示用户状态
const (
	UserOnline = iota
	UserOutLine
	UserBusyStatus
)

type Message struct {
	Type string `json:"type"`
	Data string `json:"data"`
}

type LoginMessage struct {
	UserId int `json:"userId"`
	UserPwd string `json:"userPwd"`
	UserName string `json:"type"`
}

type LoginResMessage struct {
	Code int `json:"code"` //200表示登录成功500表示用户不存在
	UserIds []int `json:"userIds"` //保存用户id
	Error string `json:"error"`
}

type RegisterMessage struct {
	UserId int `json:"userId"`
	UserPwd string `json:"userPwd"`
	UserName string `json:"userName"`
}
type RegisterResMessage struct {
	Code int `json:"code"`
	Error string `json:"error"`
}

//推送用户状态变化
type NotifyUserStatus struct {
	UserId int `json:"userId"`
	Status int `json:"status"`
}

//发送消息
type SmsMessage struct {
	Content string `json:"content"`
	User //继承User
}
