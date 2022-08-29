package main

import "fmt"
import "TheDataStructure/stack"

//运算
func cal(num1, num2, oper int) int {
	var res int
	switch oper {
	case 43:
		res = num1 + num2
	case 45:
		res = num1 - num2
	case 42:
		res = num1 * num2
	case 47:
		res = num1 / num2
	default :
		fmt.Println("输入的运算符错误")
	}
	return res
}

//判断优先级
func priority(num1, num2 int) bool {
	if (num1 == 43 || num1 == 45) && (num2 == 42 || num2 == 47) {
		return true
	}
	if (num1 == 42 || num1 == 47) && (num2 == 42 || num2 == 47) {
		return true
	}
	return false
}

func main() {
	numStack := &stack.Stack{
		MaxSize: 20,
		Top: -1,
		Array: make([]int, 20),
	}
	operStack := &stack.Stack{
		MaxSize: 20,
		Top: -1,
		Array: make([]int, 20),
	}

	str := "2+3*3-5"

	for _, value := range str {
		fmt.Printf("%d - ", value)
	}
	fmt.Println()

	for _, value := range str {
		if value == 43 || value == 45 ||value == 42 ||value == 47 {
			if operStack.Top == -1 {
				operStack.Push(int(value))
			}else {
				if priority(int(value), operStack.Peek()) {
					num1 := numStack.Pop()
					num2 := numStack.Pop()
					oper := operStack.Pop()
					result := cal(num1, num2, oper)
					numStack.Push(result)
					operStack.Push(int(value))
				}else {
					operStack.Push(int(value))
				}
			}
		}else {
			numStack.Push(int(value) - 48)
		}
	}
	for operStack.Top != -1 {
		num1 := numStack.Pop()
		num2 := numStack.Pop()
		oper := operStack.Pop()
		result := cal(num2, num1, oper)
		numStack.Push(result)
	}
	res := numStack.Pop()
	fmt.Printf("%s = %d\n", str, res)
}
