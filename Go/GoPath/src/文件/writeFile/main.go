package main

import (
	"bufio"
	"fmt"
	"os"
)
/**
const (
	// Exactly one of O_RDONLY, O_WRONLY, or O_RDWR must be specified.
	O_RDONLY int = syscall.O_RDONLY // open the file read-only.
	O_WRONLY int = syscall.O_WRONLY // open the file write-only.
	O_RDWR   int = syscall.O_RDWR   // open the file read-write.
	// The remaining values may be or'ed in to control behavior.
	O_APPEND int = syscall.O_APPEND // append data to the file when writing.
	O_CREATE int = syscall.O_CREAT  // create a new file if none exists.
	O_EXCL   int = syscall.O_EXCL   // used with O_CREATE, file must not exist.
	O_SYNC   int = syscall.O_SYNC   // open for synchronous I/O.
	O_TRUNC  int = syscall.O_TRUNC  // truncate regular writable file when opened.清空文佳再写
)
 */
func main(){
	filePath := "src/文件/flagDemo.txt"
	//perm linux系统下的文件权限，window系统下无效
	file, err := os.OpenFile(filePath, os.O_CREATE | os.O_WRONLY, 0666)
	if err != nil {
		fmt.Println("open file error = ", err)
		return
	}

	str := "hello world\r\n"
	writer := bufio.NewWriter(file)

	for i := 0; i < 5; i++ {
		writer.WriteString(str)
	}
	//将缓存刷入磁盘
	writer.Flush()

	file.Close()
}