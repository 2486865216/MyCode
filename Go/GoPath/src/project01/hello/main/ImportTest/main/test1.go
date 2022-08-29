package main

import (
	"fmt"
	testimport "project01/hello/main/testImport"
)

func main(){
	a := testimport.TestImport(1, 2)
	fmt.Println(a)
}