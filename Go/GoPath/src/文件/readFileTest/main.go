package main

import(
	"fmt"
	"io/ioutil"
)

func main(){
	//使用ioutil.ReadFile一次性读取文件，适合小文件
	//文件的打开和关闭已经封转到了ioutil.ReadFile
	file := "src/文件/test.txt"
	content, err := ioutil.ReadFile(file)
	if err != nil {
		fmt.Println(err)
	}

	//返回的是一个byte切片
	fmt.Println(fmt.Sprintf("%s",content))
}
