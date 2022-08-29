package main

import (
	"fmt"
)
/* 
数组可以存放多个同类型数据。数组也是一种数据类型，    在G0中,数组是值类型
*/
func main(){
	var arr [10]int

	fmt.Printf("%T\n", arr)

	for i := 0; i < len(arr); i++ {
		arr[i] = i
	}

	fmt.Println(arr)
	for i := 0; i < len(arr); i++ {
		fmt.Println(arr[i])
	}
}