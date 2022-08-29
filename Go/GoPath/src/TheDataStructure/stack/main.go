package stack

import "fmt"

//使用数组模拟栈
type Stack struct {
	MaxSize int
	Top int
	Array []int
}

func (this *Stack) Push(val int) {
	if this.Top == this.MaxSize - 1 {
		fmt.Println("栈已满")
		return
	}
	this.Top++
	this.Array[this.Top] = val
}

func (this *Stack) Pop() (val int){
	if this.Top == -1 {
		fmt.Println("栈为空")
		return -1
	}
	val = this.Array[this.Top]
	this.Top--
	return
}

func (this *Stack) Peek() (val int){
	if this.Top == -1 {
		fmt.Println("栈为空")
		return -1
	}
	val = this.Array[this.Top]
	return
}

func (this *Stack) Show() {
	if this.Top == -1 {
		fmt.Println("栈为空")
		return
	}
	index := this.Top
	for ; index >= 0; index-- {
		fmt.Printf("%d -> ",this.Array[index])
	}
	fmt.Println()
}

func main() {
	stack := &Stack{
		MaxSize: 5,
		Top: -1,
		Array: make([]int, 5),
	}
	stack.Push(1)
	stack.Push(2)
	stack.Push(3)
	stack.Push(4)
	stack.Push(5)
	stack.Push(6)
	stack.Push(7)

	stack.Show()
	fmt.Println(stack.Pop())
	fmt.Println(stack.Peek())
	stack.Show()
}
