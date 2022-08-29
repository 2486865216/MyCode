package process

import(
	"TCPSocket/project01/client/model"
	common "TCPSocket/project01/common/message"
	"fmt"
)

//客户端要维护的map
var onlineUsers = make(map[int]*common.User, 10)
var CurUser model.CurUser

//处理返回的信息
func updateUserStatus(notifyUserStatus *common.NotifyUserStatus) {
	user, ok := onlineUsers[notifyUserStatus.UserId]
	if !ok {
		user = &common.User{
			UserId: notifyUserStatus.UserId,
		}
	}
	user.UserStatus = notifyUserStatus.Status
	onlineUsers[notifyUserStatus.UserId] = user

	outputOnlineUsers()
}

//显示当前在线的用户
func outputOnlineUsers() {
	fmt.Println("当前在线用户列表")
	for id, _ := range onlineUsers {
		fmt.Printf("用户id:%d\n", id)
	}
	fmt.Printf("\n\n")
}
