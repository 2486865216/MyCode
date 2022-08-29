package main

import "fmt"

func main() {
	str := "hello@GoLanguage"
	slice := str[6:]

	fmt.Println("slice = ", slice)
	fmt.Printf("slice type = %T\n", slice)

	//修改字符串
	//细节，我们转成[]byte后，可以处理英文和数字，但是不能处理中文
	//原因是[]byte字节来处理，而一个汉字，是3个字节，因此就会出现乱码
	//解决方法是将string转成[]rune即可，

	// arr := []byte(str)
	// arr[5] = '%'
	// str = string(arr)
	// fmt.Println("str = ", str)

	arr := []rune(str)
	arr[5] = '好'
	str = string(arr)
	fmt.Println("str = ", str)
}
