package main

import(
	"fmt"
	"reflect"
)

func reflectTest01(b interface{}){
	//获取类型
	rType := reflect.TypeOf(b)
	fmt.Println("type = ", rType)

	//获取值
	rValue := reflect.ValueOf(b)
	fmt.Println("value = ", rValue)
	fmt.Printf("value type =  %T\n", rValue)

	rValue2 := rValue.Int() + 100
	fmt.Println("rValue2 = ", rValue2)

	//下面我们将rValue转成interface{)
	iValue := rValue.Interface()
	iValue2 := iValue.(int)
	fmt.Println("iValue2 = ", iValue2)
}

func main(){
	//演示对（基本数据类型、interface{}、ref1ect.Value)进行反射的基本操作
	var num int = 100
	reflectTest01(num)
}