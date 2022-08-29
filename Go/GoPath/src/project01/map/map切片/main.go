package main 

import(
	"fmt"
)

func main(){
	var monster []map[string]string
	monster = make([]map[string]string, 2)

	monster[0] = make(map[string]string, 2)
	monster[1] = make(map[string]string, 2)

	monster[0]["name"] = "hello"
	monster[0]["age"] = "18"
	monster[1]["name"] = "world"
	monster[1]["age"] = "21"
	fmt.Printf("%T\n", monster)
	fmt.Println(monster)
}