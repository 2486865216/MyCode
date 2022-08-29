package main

import (
	"bufio"
	"fmt"
	"net"
	"os"
	"strings"
)

func main() {
	fmt.Println("client")
	conn, err := net.Dial("tcp", "127.0.0.1:8888")
	if err != nil {
		fmt.Println("client err = ", err)
		return
	}
	//fmt.Println("conn success ", conn)
	//一，客户端发送一行数据，然后退出
	reader := bufio.NewReader(os.Stdin) //os.Stdin标准输入
	for {
		line, err := reader.ReadString('\n')
		line = strings.Trim(line, "\n")
		if line == "exit" {
			fmt.Println("客户端退出")
			break
		}
		if err != nil {
			fmt.Println("read err = ", err)
			return
		}
		//发送
		n, err := conn.Write([]byte(line))
		if err != nil {
			fmt.Println("write err = ", err)
		}
		fmt.Printf("发送了%d字节的数据\n",n)
	}
}
