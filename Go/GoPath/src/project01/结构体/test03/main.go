package main

import(
	"fmt"
	"encoding/json"
)

type Test struct {
	Name string `json:"name"`
	Age int	`json:"age"`
}

func main(){
	var test = Test{"hello", 18}
	jsonMessage, err := json.Marshal(test)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(string(jsonMessage))
}