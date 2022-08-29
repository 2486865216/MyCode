package main

import "fmt"

func main(){
	var str string
	// var age byte
	// var flo float64
	// var a int

	// fmt.Scanln(&str)
	// fmt.Scanln(&age)
	// fmt.Scanln(&flo)
	// fmt.Scanln(&a)

	// fmt.Printf("str = %v, age = %v, flo = %v, a = %v", str, age, flo, a)


	fmt.Println("请输入:")
	fmt.Scanf("%s", &str)
	fmt.Printf("str = %v", str)
}