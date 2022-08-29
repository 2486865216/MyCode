package main

import "fmt"

type Node struct {
	val int
	next *Node
}

var head *Node = nil
var tail *Node = nil

//添加
func (this *Node) insert(val int) {
	node := &Node{
		val: val,
		next: nil,
	}
	if head == nil {
		head = node
		tail = node
	}else {
		tail.next = node
		tail = tail.next
	}
}

//删除
func delete(head *Node, val int) {
	if head == nil {
		fmt.Println("链表为空")
		return
	}
	preNode := &Node{}
	preNode.next = head
	var flag = false
	for {
		if preNode.next == nil {
			break
		}
		if preNode.next.val == val {
			flag = true
			preNode.next = preNode.next.next
		}
		preNode = preNode.next
	}
	if !flag {
		fmt.Println("要删除的值在列表中不存在")
	}
}

func show(node *Node) {
	for {
		if node == nil {
			break
		}
		fmt.Printf("%d", node.val)
		if node.next != nil {
			fmt.Print(" -> ")
		}else {
			fmt.Println()
		}
		node = node.next
	}
}

func main() {
	node := &Node{
		-1,
		nil,
	}
	node.insert(1)
	node.insert(2)
	node.insert(3)
	node.insert(4)
	node.insert(5)
	show(head)
	delete(head, 20)
	show(head)
}
