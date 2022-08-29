package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func main(){
	file1Path := "src/文件/image.jpg"
	file2Path := "src/文件/fileCopy/image.jpg"

	file, err := os.Open(file1Path)
	if err != nil {
		fmt.Println("err = ", err)
		return
	}
	reader := bufio.NewReader(file)

	file2, err2 := os.OpenFile(file2Path, os.O_WRONLY | os.O_CREATE, 6666)
	if err2 != nil {
		fmt.Println("err = ", err2)
		return
	}
	writer := bufio.NewWriter(file2)
	msg, error := io.Copy(writer, reader)
	if error != nil {
		fmt.Println("error = ", error)
	}
	fmt.Println(msg)

	file.Close()
	file2.Close()
}