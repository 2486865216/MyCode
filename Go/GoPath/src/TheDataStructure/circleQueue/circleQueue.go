package main

import (
	"errors"
	"fmt"
	"os"
)

type CircleQueue struct {
	maxSize int
	front int
	rear int
	array [5]int
}

//判断是否为空
func (this *CircleQueue) isEmpty() bool {
	return this.front == this.rear
}

//判断是否为满
func (this *CircleQueue) isFull() bool {
	return this.front == (this.rear + 1) % this.maxSize
}

//添加数据到队列
func (this *CircleQueue) Push(val int) (err error) {
	if this.isFull() {
		return errors.New("队列已满")
	}
	this.rear = (this.rear + 1) % this.maxSize
	this.array[this.rear] = val
	return
}

//获取数据
func (this *CircleQueue) Pop() (value int, err error) {
	if this.isEmpty() {
		err = errors.New("队列为空")
		return
	}
	this.front = (this.front + 1) % this.maxSize
	value = this.array[this.front]
	return
}

//遍历队列
func (this * CircleQueue) ShowQueue() {
	fmt.Println("当前队列")
	size := (this.rear - this.front + this.maxSize) % this.maxSize
	if size == 0 {
		fmt.Println("队列为空")
	}else {
		for i := 0; i < size; i++ {
			index := (this.front + i + 1) % this.maxSize
			fmt.Printf("array[%d] = %d\n", index, this.array[index])
		}
	}
}

func main() {
	circleQueue := &CircleQueue{
		maxSize: 5,
		front: 0,
		rear: 0,
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
			getQueue, err := circleQueue.Pop()
			if err != nil {
				fmt.Println(err)
			}else {
				fmt.Println("获取到的值= ", getQueue)
			}
		case 2 :
			fmt.Println("请输入要放入队列中的数据：")
			var val int
			fmt.Scanf("%d\n", &val)
			err := circleQueue.Push(val)
			if err != nil {
				fmt.Println(err)
			}else {
				fmt.Println("添加成功")
			}
		case 3 :
			circleQueue.ShowQueue()
		case 4 :
			os.Exit(0)
		default :
			fmt.Println("输入有误")
		}
	}
}
