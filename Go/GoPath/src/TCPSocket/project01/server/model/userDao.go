package model

import (
	common "TCPSocket/project01/common/message"
	"encoding/json"
	"fmt"
	"github.com/gomodule/redigo/redis"
)

var (
	MyUserDao *UserDao
)

// UserDao 对user的各种操作
type UserDao struct {
	pool *redis.Pool
}

//创建UserDao
func NewUserDao(pool *redis.Pool) (userDao *UserDao) {
	userDao = &UserDao{
		pool: pool,
	}
	return
}

//根据用户id返回user实例和error
func (this *UserDao) getUserById(conn redis.Conn, userId int) (user *common.User, err error) {
	do, err := conn.Do("HGet", "users", userId)
	if err != nil || do == nil {
		err = ERROR_USER_NOTEXISTS
		return nil, err
	}
	err = json.Unmarshal(do.([]uint8), &user)
	if err != nil {
		fmt.Println("json Unmarshal error = ", err)
		return nil, err
	}
	return
}

// Login 完成登录校验
func (this *UserDao) Login(userId int, userPwd string) (user *common.User, err error) {
	conn := this.pool.Get()
	defer conn.Close()
	user, err = this.getUserById(conn, userId)
	if err != nil || user == nil {
		return nil, err
	}
	if userPwd != user.UserPwd {
		err = ERROR_USER_PWDERROR
		return
	}
	return
}

func (this *UserDao) Register(user *common.RegisterMessage) (err error) {
	conn := this.pool.Get()
	defer conn.Close()
	_, err = this.getUserById(conn, user.UserId)
	if err == nil {
		err = ERROR_USER_EXISTS
		return
	}
	data, err := json.Marshal(user)
	if err != nil {
		return
	}
	_, err = conn.Do("HSet", "users", user.UserId, string(data))
	if err != nil {
		fmt.Println("保存用户信息错误")
		return
	}
	return
}
