package main

/*
var map = 变量名 map[key type] value type
key可以是什么类型
golang中的map,的key可以是很多种类型，比如bool,数字，string,指针，channel,还可以是只包含前面几个类型的接口，结构体，数组
通常为int、string

注意：slice,map还有function不可以，因为这几个没法用==来判断

声明是不会分配内存的，初始化需要make,分配内存后才能赋值和使用：
*/

import (
	"fmt"
)

func main() {
	var a map[string]string
	a = make(map[string]string, 10)
	a["no1"] = "hello"
	a["no2"] = "world"
	a["no3"] = "Golang"
	fmt.Println(a)

	var b map[string]string = map[string]string{
		"test": "hello",
	}
	fmt.Println(b)

	//当delete指定的key不存在时，删除不会操作，也不会报错
	//如果我们要删除map的所有key,没有一个专门的方法一次删除，可以遍历一下key,逐个删除
	//或者map=make(),make一个新的，让原来的成为垃圾，被gc回收
	delete(a, "no1")
	delete(a, "no1")
	fmt.Println(a)

	//查找
	var value, ok = a["no2"]
	if ok {
		fmt.Println(value)
	}
	fmt.Println()

	var c = make(map[string]map[string]string)
	c["stu1"] = make(map[string]string)
	c["stu2"] = make(map[string]string)
	c["stu1"]["test1"] = "hello stu1"
	c["stu2"]["test1"] = "hello stu2"

	for key, value := range c {
		fmt.Println(key)
		for key1, value1 := range value {
			fmt.Println(key1,value1)
		}
	}
}
