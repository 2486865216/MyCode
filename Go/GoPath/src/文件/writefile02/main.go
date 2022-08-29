package main

import (
	"fmt"
	"io/ioutil"
)

func main(){
	file1Path := "src/文件/test.txt"
	file2Path := "src/文件/flagDemo.txt"

	content, err := ioutil.ReadFile(file1Path)
	if err != nil {
		fmt.Println("read err = ", err)
		return
	}
	err = ioutil.WriteFile(file2Path, content, 0666)
	if err != nil {
		fmt.Println("write err = ", err)
	}
}