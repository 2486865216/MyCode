/* 
1)len:用来求长度，比如string、array、slice、map、channel
2)new(T):用来分配内存，T主要用来分配值类型，比如int、float32,.struct.返回的是指针
3)make:用来分配内存，主要用来分配引用类型，比如chan、map、slice。这个我们后面讲解。
 */
package main

import (
	"fmt"
)

func main() {
	num1 := new(int)
	fmt.Printf("num1 的值 %v, num1 的地址 %v, num1 的类型 %T \n",num1, &num1, num1)
	fmt.Println("num1 的实质的值 ", *num1)
}