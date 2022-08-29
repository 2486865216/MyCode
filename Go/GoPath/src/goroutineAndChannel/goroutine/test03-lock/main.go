package main

import "fmt"
import "sync"
import "time"

//往运行某个程序时，如何知道是否存往资源竞争间题。方法很简单，在编译该程序时，增加个参效-race即可
//go build -race xxx.go
//需求：现在要计算1-200的各个数的阶乘，并且把各个数的阶乘放入到map中。
//最后显示出来。要求使用goroutine完成

//思路
//1. 编写一个函数，来计算各个数的阶乘，并放入到map中.
//2.我们启动的协程多个，统计的将结果放入到map中
//3.map应该做出一个全局的.

var(
	myMap = make(map[int]int, 10)
	//声明一个全局的互斥锁
	lock = sync.Mutex{}
)

func test(n int){
	res := 1
	for i := 1; i <= n; i++ {
		res *= i
	}
	//加锁
	lock.Lock()
	myMap[n] = res
	lock.Unlock()
}

func main(){
	for i := 0; i < 10; i++ {
		go test(i)
	}

	//睡眠，防止主线程先退出
	time.Sleep(time.Second * 5)

	lock.Lock()
	for index, value := range myMap {
		fmt.Printf("myMap[%d]=%d\n", index, value)
	}
	lock.Unlock()
}