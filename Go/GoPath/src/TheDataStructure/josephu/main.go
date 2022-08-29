package main

import "fmt"

type Node struct {
	no int
	next *Node
}

func createNode(num int) *Node {

	first := &Node{}
	curNode := first

	if num < 1 {
		fmt.Println("输入的num不对")
		return first
	}

	for i := 1; i <= num; i++ {
		node := &Node{
			no: i,
		}
		if i == 1 {
			first = node
			curNode = node
			curNode.next = first
		}else {
			curNode.next = node
			curNode = curNode.next
			curNode.next = first
		}
	}
	return first
}

func show(first *Node) {
	if first.next == nil {
		fmt.Println("链表为空")
	}

	curNode := first
	for {
		fmt.Printf("%d -> ", curNode.no)
		curNode = curNode.next
		if curNode == first {
			return
		}
	}
}

func playGame(first *Node, startNo , countNum int) {
	if first.next == nil {
		fmt.Println("空链表")
		return
	}

	tail := first

	//记录尾部
	for {
		if tail.next == first {
			break
		}
		tail = tail.next
	}

	//移动到startNo
	for i := 0; i < startNo - 1; i++ {
		first = first.next
		tail = tail.next
	}

	//开始数countNum
	for {
		for i := 1; i < countNum; i++ {
			first = first.next
			tail = tail.next
		}
		fmt.Printf("编号%d出圈 -> ", first.no)
		first = first.next
		tail.next = first
		if tail == first {
			break
		}
	}
	fmt.Println("最后的:", first.no)
}

func main() {
	first := createNode(5)
	show(first)
	playGame(first, 1, 3)
}
