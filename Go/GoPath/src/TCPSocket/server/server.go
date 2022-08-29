package main

import (
	"fmt"
	"net"
)

func process(conn net.Conn){
	defer conn.Close()
	for {
		buffer := make([]byte, 1024)
		n ,err := conn.Read(buffer)
		//fmt.Println("等待", conn.RemoteAddr().String())
		if err != nil {
			fmt.Println("process err = ", err)
			return
		}
		fmt.Print(string(buffer[:n]) + "\n")
	}
}

func main(){
	fmt.Println("开始监听")
	listen, err := net.Listen("tcp", "127.0.0.1:8888")
	if err != nil {
		println("listen err = ", err)
		return
	}
	//fmt.Println("listen = ", listen)
	defer listen.Close() // 关闭
	for {
		//等待连接
		//fmt.Println("等待连接")
		conn, err1 := listen.Accept()
		if err1 != nil {
			fmt.Println("accept err = ", err1)
			return
		}else {
			//fmt.Println(conn.RemoteAddr().String(), conn.RemoteAddr().Network())
		}
		go process(conn)
	}
}