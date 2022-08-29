package main

import (
	"fmt"
	"unsafe"
)

// var test = "test"
// var test1 = "test1"
// var test2 = "test2"

var(
	test = "test"
	test1 = "test1"
	test2 = "test2"
)

func main(){
	// var i int = 4
	// fmt.Println("i = ", i)

	//省略var
	// j := 5
	// fmt.Println("j = ", j)

	// var n1, name, n3 = 9, "tom", 100
	// fmt.Println("n1 = ",n1, "name = ", name, "n3 = ", n3)

	// n1, name, n3 := 9, "tom", 100
	// fmt.Println("n1 = ",n1, "name = ", name, "n3 = ", n3)

	fmt.Println("test = ", test, "test1 = ", test1, "test2 = ", test2)

	var c byte = 255
	fmt.Println("c = ", c)

	fmt.Println("hello world!")

	//unsafe.sizeof(nl)是unsafe包的一个函数，可以返回nl变量占用的字节数
	var test string = "test"
	fmt.Printf("test 的类型 %T test 的大小 %d", test, unsafe.Sizeof(test))
}