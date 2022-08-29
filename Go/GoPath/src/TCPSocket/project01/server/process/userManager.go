package process

import "fmt"

var(
	userManager *UserManager
)

type UserManager struct {
	OnlineUsers map[int]*UserProcess
}

//初始化
func init() {
	userManager = &UserManager{
		OnlineUsers: make(map[int]*UserProcess, 1024),
	}
}

//添加
func (this *UserManager) AddOnlineUser(user *UserProcess) {
	this.OnlineUsers[user.UserId] = user
}

//删除
func (this *UserManager) DeleteOnlineUser(user *UserProcess) {
	delete(this.OnlineUsers, user.UserId)
}

//返回当前所有在线的用户
func (this *UserManager) GetAllOnlineUser() map[int]*UserProcess {
	return this.OnlineUsers
}

//根据id返回
func (this *UserManager) GetUserById(userId int) (user *UserProcess, err error) {
	user, ok := this.OnlineUsers[userId]
	if !ok {
		err = fmt.Errorf("用户%d,不存在", userId)
		return
	}
	return
}
