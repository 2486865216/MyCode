package main

/* 
1)切片的英文是slice

2)切片是数组的一个引用，因此切片是引用类型，在进行传递时，遵守引用传递的机制。

3)切片的使用和数组类似，遍历切片、访问切片的元素和求切片长度len(slice)都一样。

4)切片的长度是可以变化的，因此切片是一个可以动态变化数组。

5)切片定义的基本语法：
var 变量名 []类型
比如：var a []int

type slice struct{
	ptr *[]int
	len int
	cap int
}


第一种方式：定义一个切片，然后让切片去引用一个己经创建好的数组，

第二种方式：通过make来创建切片
	基本语法：var 切片名 []]type = make([]type,len,[cap])
		参数说明：type:就是数据类型len:大小cap:指定切片容量，可选

第三种方式：定义一个切片，直接就指定具体数组，使用原理类似make的方式。
*/
import (
	"fmt"
)

func main(){
	var intArr = [...]int{0, 1, 2, 3, 4, 5, 6, 7}
	slice := intArr[1:3]
	fmt.Println("slice = ", slice)
	fmt.Println("slice 长度 ", len(slice))
	//cap是从第个元素地址开始算到基层数组最后一个元素
	fmt.Println("slice 容量 ", cap(slice))
	fmt.Printf("slice type %T\n", slice)

	//make
	var slice2 []int = make([]int, 5, 10)
	slice2[0] = 1
	fmt.Println("slice2 = ", slice2)

	slice3 := [...]int{0, 1, 2, 3, 4, 5}
	fmt.Println("slice3 = ", slice3)
	fmt.Println("slice3 size() ", len(slice3))
	fmt.Println("slice3 cap() ", cap(slice3))
}