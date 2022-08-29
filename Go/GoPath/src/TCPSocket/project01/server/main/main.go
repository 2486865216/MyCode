package main

import (
	"TCPSocket/project01/server/model"
	"fmt"
	"time"
)
import "net"


func processMain(conn net.Conn){
	defer conn.Close()
	process := &Processor{
		conn,
	}
	err := process.process()
	if err != nil {
		return
	}
}

func initUserDao() {
	model.MyUserDao = model.NewUserDao(pool)
}

func main() {
	initPool("192.168.229.130:6379", 8, 0, 300 * time.Second)
	initUserDao()
	fmt.Println("服务器在8888端口....")
	listen, err := net.Listen("tcp", "127.0.0.1:8888")
	if err != nil {
		fmt.Println("listen error = ", err)
		return
	}

	//延时关闭
	defer listen.Close()

	for {
		fmt.Println("等待客户端连接......")
		conn, err := listen.Accept()
		if err != nil {
			fmt.Println("Accept error = ", err)
			return
		}
		go processMain(conn)
	}
}
