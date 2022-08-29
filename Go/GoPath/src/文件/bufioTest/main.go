package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func main(){
	file, err := os.Open("src/文件/test.txt")
	if err != nil {
		fmt.Println("err = ", err)
	}
	fmt.Println(file)

	defer file.Close()

	//默认大小
	//const (
	//	defaultBufSize = 4096
	//)
	reader := bufio.NewReader(file)
	for{
		str, err := reader.ReadString('\n') //读到一个换行就结束
		fmt.Print(str)
		if err == io.EOF {
			break
		}
	}
}
