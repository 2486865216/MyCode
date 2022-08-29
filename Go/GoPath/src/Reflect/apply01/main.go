package main

import (
	"fmt"
	"reflect"
)

type Monster struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
	Sex  string
}

//打印
func (m Monster) Print() {
	fmt.Println("---Start---")
	fmt.Println(m)
	fmt.Printf("%p\n",&m)
	fmt.Println("---End---")
}

//显示两个数的和
func (m Monster) GetSum(n1, n2 int) int {
	return n1 + n2
}

func (m Monster) Set(name string, age int, sex string) {
	m.Name = name
	m.Age = age
	m.Sex = sex
}

func TestStruct(arg Monster){
	argType := reflect.TypeOf(arg)

	argValue := reflect.ValueOf(arg)

	argKind :=argType.Kind()

	if argKind != reflect.Struct {
		fmt.Println("类型不符")
		return
	}
	//获取字段数
	num := argValue.NumField()
	fmt.Println("字段数:", num)
	//遍历所有字段
	for i := 0; i < num; i++ {
		fmt.Printf("Field: %d, value = %v\n", i, argValue.Field(i))
		//获取标签
		tagValue := argType.Field(i).Tag.Get("json")
		if tagValue != "" {
			fmt.Printf("Field: %d, tagValue = %s\n", i, tagValue)
		}
	}

	numMethods := argValue.NumMethod()
	fmt.Printf("struct has %d methods\n", numMethods)

	//方法按函数名排序
	argValue.Method(1).Call(nil)

	var params = make([]reflect.Value, 2)
	params[0] = reflect.ValueOf(10)
	params[1] =reflect.ValueOf(40)
	res := argValue.Method(0).Call(params)//接受的参数是[]reflect.Value, 返回的结果是[]reflect.Value
	fmt.Println("res=", res[0].Int())
}

func main(){
	var test = Monster{
		"Hello World",
		18,
		"男",
	}
	TestStruct(test)
}
