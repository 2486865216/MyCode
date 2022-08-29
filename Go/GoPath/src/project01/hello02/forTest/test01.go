package main

import "fmt"

func main(){
	// for i := 1; i <= 10; i++ {
	// 	fmt.Println(i)
	// }

	// var i = 1
	// for i <= 10 {
	// 	fmt.Println(i)
	// 	i++
	// }

	// var str1 = "abcdefghijklmn"
	// str2 := []rune(str1)

	// for i := 0; i < len(str2); i++ {
	// 	fmt.Printf("%c\n",str2[i])
	// }

	// var str = "hello world go!"
	// for index, val := range str {
	// 	fmt.Printf("index = %v, val = %c\n", index, val)
	// }

	//go没有while
	// var i = 1
	// for {
	// 	if i > 10 {
	// 		break //跳出循环
	// 	}
	// 	fmt.Println("Hello World!")
	// 	i++
	// }

	//do..while
	var i = 1
	for {
		fmt.Println("Hello World! = ", i)
		i++
		if i > 10 {
			break //跳出循环
		}
	}
}