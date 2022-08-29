package main

import "fmt"

func main(){
	var intchan chan int
	intchan = make(chan int, 3)
	fmt.Printf("inchan的值：%v, inchan的地址: %p\n", intchan, &intchan)

	//将数据写入channel
	//写入数据的多少不能超过管道长度
	intchan <- 10
	num := 20
	intchan <- num
	fmt.Printf("管道的长度:%d,管道的容量:%d\n", len(intchan), cap(intchan))

	//读取
	var num2 = <-intchan
	fmt.Println("num2 = ",num2)
	//在没有使用协程的情况下，如果我们的管道数据已经全部取出，再取就会报告deadlock
}