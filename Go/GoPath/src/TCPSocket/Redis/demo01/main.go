package main

import(
	"fmt"
	"github.com/gomodule/redigo/redis"
)

func main() {
	//连接redis
	conn, err := redis.Dial("tcp", "192.168.229.130:6379")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer conn.Close()
	fmt.Println(conn)

	//操作redis
	do, err := redis.String(conn.Do("set", "name", "hello"))
	if err != nil {
		return
	}
	fmt.Println("do ", do)
}
