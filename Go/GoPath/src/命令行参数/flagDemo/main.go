/*
flag包用来解析命令行参数
说明：前面的方式是比较原生的方式，对解析参数不是特别的方便，特别是带有指定参数形式的命令行。

go设计者给我们提供了flag包，可以方便的解析命令行参数，而且参数顺序可以随意
 */
package main

import (
	"flag"
	"fmt"
)

func main(){
 	//接受参数
	var user string
	var password string
	var host string
	var port string

	//user就是接收用户命令行中输入的-u后面的参数值
	//"u”,就是-u指定参数
	//"” 默认值
	//说明
	flag.StringVar(&user, "u", "", "用户名，默认为空")
	flag.StringVar(&password, "pwd", "", "密码，默认为空")
	flag.StringVar(&host, "h", "localhost", "主机")
	flag.StringVar(&port, "p", "3306", "端口")

	//这里有一个非常重要的操作，转换， 必须调用该方法
	flag.Parse()

	fmt.Printf("user=%v, password=%v, host=%v, port=%v\n", user, password, host, port)
}