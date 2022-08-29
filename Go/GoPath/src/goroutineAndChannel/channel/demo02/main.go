package main

import "fmt"
import "time"

//统计素数

func putNum(intChan chan int){
	for i :=0; i < 8000; i++ {
		intChan<-i
	}
	close(intChan)
}
//判断是否是素数
func primeNum(intChan chan int, primeChan chan int, exitChan chan bool){
	for {
		num, ok := <- intChan
		if !ok {
			//time.Sleep(time.Millisecond * 100)
			fmt.Println("primeNum::",ok)
			break
		}
		flag := true
		for i := 2; i < num; i++ {
			if num % i == 0 {
				flag = false
				break
			}
		}
		if flag {
			primeChan <- num
		}
	}
	fmt.Println("有一个primeNUm协程退出")
	exitChan <- true
}
func main(){
	start := time.Now().UnixMilli()
	intChan := make(chan int, 10000)
	primeChan := make(chan int, 2000)
	//标识退出
	exitChan := make(chan bool, 4)
	go putNum(intChan)
	for i :=0; i < 4; i++ {
		go primeNum(intChan, primeChan, exitChan)
	}

	go func(){
		for i :=0; i < 4; i++ {
			<-exitChan
		}
		close(primeChan)
	}()
	//遍历primeChan
	for {
		value, ok := <- primeChan
		fmt.Println("main ok :: ",ok)
		if !ok {
			break
		}
		fmt.Println(value)
	}
	close(exitChan)
	fmt.Println("耗费的时间=",time.Now().UnixMilli() - start,"ms")
}