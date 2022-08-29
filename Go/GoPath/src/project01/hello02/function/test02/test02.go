package main

import "fmt"

/*
函数注意事项和细节讨论
	1)函数的形参列表可以是多个，返回值列表也可以是多个。

	2)形参列表和返回值列表的数据类型可以是值类型和引用类型。

	3)函数的命名遵循标识符命名规范，首字母不能是数字，首字母大写该函数可以被
	本包文件和其它包文件使用，类似public,首字母小写，只能被本包文件使用，其它包文件不能使用，类似private。

	4)函数中的变量是局部的，函数外不生效

	5)基本数据类型和数组默认都是值传递的，即进行值拷贝。在函数内修改，不会影响到原来的值。

	6)如果希望函数内的变量能修改函数外的变量，可以传入变量的地址&，函数内以指针的方式操作变量。从效果上看类似引用

	7)G0函数不支持重载。
*/

//8)在G0中，函数也是、种数据类型，可以赋值给一个变量，则该变量就是一个函数类型的变量了。通过该变量可以对函数调用。
func test8(a int, b int) int{
	return a + b
}

//9)函数既然是一种数据类型，因此在G0中，函数可以作为形参，并且调用！
func test9(sum func(int, int) int, a int, b int) int {
	return sum(a, b)
}

/*10)为了简化数据类型定义，Go支持自定义数据类型基本语法：type 自定义数据类型名 数据类型
∥理解：相当于一个别名
案例：type myInt int //这时myInt就等价int来使用了.   
	给int取了别名，在go中myInt和int虽然都是int类型，但是go认为nyInt和int是两个类型
	相互复制需要显式转换
案例：type mySum func(int,int) int //这时mySum就等价一个函数类型func(int,int) int
11)支持对函数返回值命名

12)使用_标识符，忽略返回值
	d, _ := test11(30, 40)
*/

func test11 (n1 int, n2 int) (sum int, sub int){
	sum = n1 + n2
	sub = n1 - n2
	return
}

//	13)60支持可变参数
//0个或多个
// func test13 (args... int) (sum int) {

// }
func test13 (n1 int, args... int) (sum int) {
	sum = n1
	for i := 0; i < len(args); i++ {
		sum += args[i]
	}
	return
}


func main() {

	a := test8
	fmt.Printf("a的类型是%T\n", a)
	b := a(1, 3)
	fmt.Printf("b = %d\n", b)

	c := test9(a, 4, 5)
	fmt.Printf("c = %d\n", c)

	d, f := test11(30, 40)
	fmt.Printf("d = %d\n", d)
	fmt.Printf("f = %d\n", f)

	e := test13(1, 2, 3)
	fmt.Printf("e = %d\n", e)

	//匿名函数
	result := func (a int, b int) int {
		return a + b
	}(10, 20)

	fmt.Printf("result = %d\n", result)

	g := func (a int, b int) int {
		return a + b
	}
	result2 := g(20, 50)
	fmt.Printf("result2 = %d\n", result2)
}

