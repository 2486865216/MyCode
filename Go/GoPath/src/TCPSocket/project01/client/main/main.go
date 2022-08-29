package main

import (
	"TCPSocket/project01/client/process"
	"fmt"
	"os"
)

var (
	userId  int
	userPwd string
	userName string
)

func main() {
	//接受用户的选择
	var key int
	for {
		fmt.Println("=====多人聊天系统=====")
		fmt.Println("\t1.登录聊天室")
		fmt.Println("\t2.注册用户")
		fmt.Println("\t3.退出系统")
		fmt.Println("\t请选择（1-3）")
		fmt.Scanf("%d\n", &key)
		switch key {
		case 1:
			fmt.Println("登录聊天室")
			user := &process.UserProcess{}
			fmt.Println("请输入用户的id")
			fmt.Scanf("%d\n", &userId)
			fmt.Println("请输入用户的密码")
			fmt.Scanf("%s\n", &userPwd)
			err := user.Login(userId, userPwd)
			if err != nil {
				fmt.Println("登陆失败")
			}
		case 2:
			fmt.Println("注册用户")
			fmt.Println("请输入用户id")
			fmt.Scanf("%d\n", &userId)
			fmt.Println("请输入用户密码")
			fmt.Scanf("%s\n", &userPwd)
			fmt.Println("请输入用户名")
			fmt.Scanf("%s\n", &userName)
			//注册
			user := &process.UserProcess{}
			user.Register(userId, userPwd, userName)
		case 3:
			fmt.Println("退出系统")
			os.Exit(0)
		default:
			fmt.Println("你的输入有误，请重新输入")
		}
	}
}
