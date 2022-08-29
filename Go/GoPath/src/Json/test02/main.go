package main

import (
	"encoding/json"
	"fmt"
)

var testStruct = "{\"name\":\"hello world\",\"age\":18,\"email\":\"java\"}"
var testMap = "{\"age\":\"20\",\"name\":\"java\"}"
var testSlice = "[{\"age\":30,\"name\":\"java\"},{\"age\":20,\"name\":\"python\"}]"

type Test struct {
	Name  string `json:"name"`
	Age   int    `json:"age"`
	Email string `json:"email"`
}

func unjsonStruct(){
	var test Test
	err := json.Unmarshal([]byte(testStruct), &test)
	if err != nil {
		fmt.Println("Unmarshal error = ", err)
		return
	}
	fmt.Println(test)
}

func unjsonMap(){
	var test map[string]interface{}
	err := json.Unmarshal([]byte(testMap), &test)
	if err != nil {
		fmt.Println("Unmarshal error = ", err)
		return
	}
	fmt.Println(test)
}

func unjsonSlice(){
	var test []map[string]interface{}
	err := json.Unmarshal([]byte(testSlice), &test)
	if err != nil {
		fmt.Println("Unmarshal error = ", err)
		return
	}
	fmt.Println(test)
}

func main(){
	unjsonStruct()
	unjsonMap()
	unjsonSlice()
}