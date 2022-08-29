package main

import (
	"errors"
	"fmt"
	"os"
)

type Queue struct {
	maxSize int
	array [4]int
	front int
	rear int
}

//添加数据到队列
func (this *Queue) AddQueue(val int) (err error) {
	if this.rear == this.maxSize - 1 {
		return errors.New("queue full")
	}
	this.rear++
	this.array[this.rear] = val
	return
}

//获取数据
func (this *Queue) GetQueue() (value int, err error) {
	if this.rear == this.front {
		err = errors.New("queue empty")
		return
	}
	this.front++
	value = this.array[this.front]
	return
}

//遍历队列
func (this * Queue) ShowQueue() {
	fmt.Println("当前队列")
	for i := this.front + 1; i <= this.rear; i++ {
		fmt.Printf("array[%d] = %d\n", i, this.array[i])
	}
}

func main() {
	queue := &Queue{
		maxSize: 4,
		front: -1,
		rear: -1,
	}

	var key int
	for {
		fmt.Println("1。获取数据")
		fmt.Println("2。添加数据")
		fmt.Println("3。显示数据")
		fmt.Println("4。退出")

		fmt.Scanf("%d\n", &key)

		switch key {
		case 1 :
			getQueue, err := queue.GetQueue()
			if err != nil {
				fmt.Println(err)
			}else {
				fmt.Println("获取到的值= ", getQueue)
			}
		case 2 :
			fmt.Println("请输入要放入队列中的数据：")
			var val int
			fmt.Scanf("%d\n", &val)
			err := queue.AddQueue(val)
			if err != nil {
				fmt.Println(err)
			}else {
				fmt.Println("添加成功")
			}
		case 3 :
			queue.ShowQueue()
		case 4 :
			os.Exit(0)
		default :
			fmt.Println("输入有误")
		}
	}
}
