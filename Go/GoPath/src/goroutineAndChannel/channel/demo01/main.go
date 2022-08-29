/*开启一个writeData协程，向管道intChan中写入50个整数
开启一个readData协程，从管道intChan中读取writeData写入的数据，
注意：writeData和readDate:操作的是同一个管道
主线程需要等待writeData和readDate协程都完成工作才能退出*/
package main

import "fmt"

func writeData(intChan chan int){
	for i := 0; i < 50; i++ {
		fmt.Println("write Data ", i)
		intChan <- i
	}
	close(intChan)
}

func readData(intChan chan int, exitChan chan bool){
	for {
		value, ok := <- intChan
		if !ok {
			break
		}
		fmt.Println(value)
	}
	exitChan <- true
}
func main(){
	intChan := make(chan int, 50)
	exitChan := make(chan bool, 1)

	go writeData(intChan)
	go readData(intChan, exitChan)

	for {
		value := <- exitChan
		if value {
			break
		}
	}
}