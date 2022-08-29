package main

import (
	"encoding/json"
	"fmt"
)

type Test struct {
	Name  string `json:"name"`
	Age   int    `json:"age"`
	Email string `json:"email"`
}

func jsonStruct() {
	test01 := Test{
		Name:  "hello world",
		Age:   18,
		Email: "java",
	}
	//序列化
	data, err := json.Marshal(&test01)
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Println(string(data))
}

func jsonMap() {
	var a map[string]interface{}
	a = make(map[string]interface{})
	a["name"] = "java"
	a["age"] = "20"
	//序列化
	data, err := json.Marshal(&a)
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Println(string(data))
}

func jsonSlice() {
	var slice []map[string]interface{}
	var m1 map[string]interface{} = make(map[string]interface{})
	var m2 map[string]interface{} = make(map[string]interface{})
	m1["name"] = "java"
	m2["name"] = "python"
	m1["age"] = 30
	m2["age"] = 20
	slice = make([]map[string]interface{}, 2)
	slice[0] = m1
	slice[1] = m2
	//序列化
	data, err := json.Marshal(&slice)
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Println(string(data))
}
func main() {
	jsonStruct()
	jsonMap()
	jsonSlice()
}
