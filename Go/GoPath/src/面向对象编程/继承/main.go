package main

import "fmt"

// 也就是说：在Golang中，如果一个struct嵌套了另一个匿名结构体，
//那么这个结构体可以直接访问匿名结构体的字段和方法，从而实现了继承特性。

type Student struct {
	Name string
	Age int
}

func (s *Student) showInfo(){
	fmt.Printf("student name = \"%s\", student age = \"%d\"\n", s.Name, s.Age)
}

type College struct {
	Student //嵌入了Student匿名结构体
	Name string
}

/* 
当结构体和匿名结构体有相同的字段或者方法时，编译器采用就近访问原则访问，如希望访问匿名
结构体的字段和方法，可以通过匿名结构体名来区分

结构体嵌入两个（或多个）匿名结构体，如两个匿名结构体有相同的字段和方法（同时结构体本身没有同名的字段和方法)，
在访问时，就必须明确指定匿名结构体名字，否则编译报错。
*/

func main(){
	test := College{}
	test.Student.Name = "hello world"
	test.Age = 18
	test.Name = "hello go"
	fmt.Println(test)
	test.showInfo()

	fmt.Println(test.Student.Name)
	fmt.Println(test.Name)

	//嵌套匿名结构体后，也可以在创建结构体变量（实例）时，直接指定各个匿名结构体字段的值
	test1 := College{Student{"java", 28}, "c++"}
	fmt.Println(test1)
}