package main

//Golang的结构体没有构造函数，通常可以使用工厂模式来解决这个问题。
import (
	"fmt"
	"project01/结构体/工厂模式/model"
)

func main(){
	// stu := model.Student{
	// 	Name: "hello world",
	// 	Age: 18,
	// }

	stu := model.NewStudent("hello world", 18)
	fmt.Println(stu)
	fmt.Println(*stu)
	fmt.Println(stu.Name, stu.Age)
}