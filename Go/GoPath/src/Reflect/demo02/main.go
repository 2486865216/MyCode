package main

import (
	"fmt"
	"reflect"
)

type Student struct{
	Name string
	Age int
}
/*
1)reflect.Value.Kind,获取变量的类别，返回的是一个常量

2)Type是类型，Kind是类别，Type和Kind可能是相同的，也可能是不同的.
比如：var num int = 10, num的Type是int,Kind也是int
比如：var stu Student stu的Type是 包名.Student,Kind是struct

3)通过反射可以在让变量在interface}和Reflect.Value之间相互转换，

4)使用反射的方式来获取变量的值（并返回对应的类型），要求数据类型匹配，比如x是it,
那么就应该使用reflect.Value(x).Int(),而不能使用其它的，否则报panic

5)通过反射的来修改变量，注意当使用SetXxx方法来设置需要通过对应的指针类型来
完成，这样才能改变传入的变量的值，同时需要使用到reflect..Value.Elem()方法
*/
func reflectTest(args interface{}){
	//获取类型
	rType := reflect.TypeOf(args)
	fmt.Println("type = ", rType)
	fmt.Println("type kind = ", rType.Kind())

	//获取值
	rValue := reflect.ValueOf(args)
	fmt.Println("value = ", rValue)
	fmt.Println("value kind = ", rValue.Kind())
	fmt.Printf("value type =  %T\n", rValue)

	//下面我们将rValue转成interface{)
	iValue := rValue.Interface()
	fmt.Printf("iValue type = %T\n", iValue)
	iValue2 := iValue.(Student)
	fmt.Printf("Name = %s, Age = %d\n", iValue2.Name, iValue2.Age)
}

func reflectTest02(args interface{}){
	/*
	5)通过反射的来修改变量，注意当使用SetXxx方法来设置需要通过对应的指针类型来
	完成，这样才能改变传入的变量的值，同时需要使用到reflect.Value.Elem()方法
	*/
	rType := reflect.ValueOf(args)
	fmt.Println("rType kind = ", rType.Kind())
	rType.Elem().SetInt(50)
}

func main(){
	student := Student{
		"Tom",
		18,
	}
	reflectTest(student)

	fmt.Println()

	var num = 100
	reflectTest02(&num)
	fmt.Println("num = ",num)
}