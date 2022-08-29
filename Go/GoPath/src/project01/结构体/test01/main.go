package main

/* 
Golang也支持面向对象编程(OOP),但是和传统的面向对象编程有区别，并不是纯粹的面向对象语言。
所以我们说Golang支持面向对象编程特性是比较准确的。

Golang没有类(class),Go语言的结构体(struct)和其它编程语言的类(class)有同等的地位，你可以理解Golang是基于struct来实现OOP特性的。

Golang面向对象编程非常简洁，去掉了传统OOP语言的继承、方法重载、构造函数和析构函数、隐藏的this指针等等

Golang仍然有面向对象编程的继承，封装和多态的特性，只是实现的方式和其它O0P语言不一样，比如继承：Golang没有extends关键字继承是通过匿名字段来实现。

Golang面向对象(OOP)很优雅，OOP本身就是语言类型系统(ype system)的一部分，通过接口
(interface)关联，耦合性低，也非常灵活。后面同学们会充分体会到这个特点。也就是说在Golang中面
向接口编程是非常重要的特性。
*/

import (
	"fmt"
)

//Cat结构体
type Cat struct {
	Name string
	Age int
	Color string
}
/* 
如果结构体的字段类型是：指针，slice,和map的零值都是nil,即还没有分配空间
如果需要使用这样的字段，需要先make,才能使用.
*/

type test struct {
	name string
	age int
	scores [5]int
	ptr *int
	slice []int
	map1 map[string]string
}
func main(){
	var cat1 Cat
	cat1.Name = "hello"
	cat1.Age = 18
	cat1.Color = "black"
	fmt.Println(cat1)

	var test1 test
	fmt.Println(test1)
	test1.slice = make([]int, 10)
	test1.slice[0] = 100
	fmt.Println(test1)
}