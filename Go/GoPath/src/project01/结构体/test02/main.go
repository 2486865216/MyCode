package main

import "fmt"

type person struct {
	name string
	age  int
}

func main() {
	//方式一
	var person1 person
	fmt.Println(person1)

	//方式二
	var person2 = person{}
	fmt.Println(person2)

	var person21 = person{"hello world", 18}
	fmt.Println(person21)

	//方式三
	var person3 = new(person)
	//(*p3).Name="smith"也可以这样写p3.Name="smith"
	//原因：go的设计者为了程序员使用方便，底层会对p3.Name="smith"进行处理
	//会给p3加上取值运算(*p3).Name="smith"
	fmt.Println(person3)
	fmt.Println((*person3))

	//方式四
	//下面的语句，也可以直接给字符赋值
	//var person *Person= &Person{"mary",60}
	var person4 = &person{}
	//因为person是一个指针，因此标准的访问字段的方法
	//(*person).Name "scott"
	//go的设计者为了程序员使用方便，也可以person.Name="scott"
	fmt.Println(person4)
	fmt.Println((*person4))
}
