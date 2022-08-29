package main

import "github.com/gomodule/redigo/redis"

var pool = &redis.Pool{
	MaxIdle: 8, //最大空闲连接数
	MaxActive: 0, //最大连接数， 0表示没有限制
	IdleTimeout: 100, //最大空闲时间
	Dial: func() (redis.Conn, error) {//初始化连接
		return redis.Dial("tcp", "192.168.229.130:6379")
	},
}

func main() {
	//获取连接
	conn := pool.Get()
	defer conn.Close()

	conn.Do("set", "test", "tom")
}
