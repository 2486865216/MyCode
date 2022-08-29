package main

import (
	"fmt"
)

func main() {
	intChan := make(chan int, 10)
	strChan := make(chan string, 5)
	for i := 0; i < 10; i++ {
		intChan <- i
	}
	for i := 0; i < 5; i++ {
		strChan <- "string" + fmt.Sprint(i)
	}
	for {
		//注意：这里，如果intChan一直没有关闭，不会一直阻塞而deadlock
		//,会自动到下一个case匹配
		select {
			case v := <- intChan:
				fmt.Printf("从intChan中获取到值%d\n", v)
			case v := <- strChan:
				fmt.Printf("从strChan中获取到值%s\n", v)
			default:
				fmt.Println("都取不到")
				return
		}
	}
}
