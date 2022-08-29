package main
//在主线程（可以理解成进程）中，开启一个goroutine,该协程每隔1秒输出"hello,world'
//在主线程中也每隔一秒输出"hel1o,golang”,输出10次后，退出程序
//要求主线程和goroutine同时执行
import "fmt"
import "time"
import "strconv"

func test(){
	for i := 0; i < 10; i++ {
		fmt.Println("test() hello world" + strconv.Itoa(i))
		time.Sleep(time.Second)
	}
}

func main(){

	go test() //开启一个线程

	for i := 0; i < 10; i++ {
		fmt.Println("main hello world" + strconv.Itoa(i))
		time.Sleep(time.Second)
	}
	/*
	1)主线程是一个物理线程，直接作用在cpu上的。是重量级的，非常耗费cpu资源。

	2)协程从主线程开启的，是轻量级的线程，是逻辑态。对资源消耗相对小。

	3)Golang的协程机制是重要的特点，可以轻松的开启上万个协程
	其它编程语言的并发机制是一般基于线程的，开启过多的线程，资源耗费大，这里就突显Golang在并发上的优势了
	*/
}