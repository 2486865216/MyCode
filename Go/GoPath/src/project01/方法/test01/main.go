package main

/* 
在某些情况下，我们要需要声明（定义）方法。比如Person结构体：除了有一些字段外（年龄，姓名)，Person结构体还有一些行为比如：可以说话、跑步，通过学习，还可以做算术题。这
时就要用方法才能完成。

Golang中的方法是作用在指定的数据类型上的（即：和指定的数据类型绑定），因此自定义类型，都可以有方法，而不仅仅是struct

方法的声明和调用
type A struct{
	Num int
}

func (a A) test(){
	fmt.Println(a.Num)
}

方法的调用和传参机制原理：(重要！)

说明：方法的调用和传参机制和函数基本一样，不一样的地方是方法调用时，会将调用方法的变量，
当做实参也传递给方法(拷贝一份)。下面我们举例说明。

1)结构体类型是值类型，在方法调用中，遵守值类型的传递机制，是值拷贝传递方式

2)如程序员希望在方法中，修改结构体变量的值，可以通过结构体指针的方式来处理

3)Golang中的方法作用在指定的数据类型上的（即：和指定的数据类型绑定），
因此自定义类型，都可以有方法，而不仅仅是struct,.比如int,float32等都可以有方法

4)方法的访问范围控制的规则，和函数一样。方法名首字母小写，只能在本包访问，方法首字母大写，可以在本包和其它包访问。

5)如果一个变量实现了String()这个方法，那么fmt.Println默认会调用这个变量的String()进行输出

*/

import "fmt"

type A struct{
	Num int
}

func (a A) test(){
	a.Num = 18
	fmt.Println(a.Num)
	fmt.Println(a)
	fmt.Printf("%p\n",&a)
}

func main(){
	var test1 = A{}
	test1.test()
	fmt.Println(test1.Num)
	fmt.Printf("%p\n",&test1)
}