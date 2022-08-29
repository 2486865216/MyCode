package main

import "fmt"

//双向链表

type Node struct {
	val int
	pre *Node
	next *Node
}

func insert(head *Node, val int) {
	currentNode := head
	newNode := &Node{
		val: val,
	}
	for {
		if currentNode.next == nil {
			break
		}
		currentNode = currentNode.next
	}
	currentNode.next = newNode
	newNode.pre = currentNode
}

func delete(head *Node, val int) {
	flag := false
	currentNode := head
	for {
		if currentNode == nil {
			break
		}
		if currentNode.val == val {
			flag = true
			currentNode.next.pre = currentNode.pre
			currentNode.pre.next = currentNode.next
		}
		currentNode = currentNode.next
	}
	if !flag {
		fmt.Println("要删除的值不存在")
	}
}

func show(head *Node) {
	currentNode := head
	fmt.Println("当前链表:")
	for {
		if currentNode == nil {
			return
		}
		fmt.Printf(" <- %d -> ", currentNode.val)
		currentNode = currentNode.next
	}
}

func main() {
	head := &Node{
		val: 1,
	}
	insert(head, 2)
	insert(head, 3)
	insert(head, 4)
	insert(head, 5)

	delete(head, 6)

	show(head)
}
