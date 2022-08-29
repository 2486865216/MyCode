package main

import "fmt"

func main(){
	var i int = 1
	fmt.Printf("i 的内存地址= %p\n", &i)

	var ptr *int = &i
	fmt.Printf("ptr = %v\n", ptr)

	fmt.Printf("ptr指向的值 = %v\n", *ptr)

	*ptr = 2
	fmt.Printf("ptr指向的值 = %v\n", *ptr)
	fmt.Printf("i = %v\n", i)
}