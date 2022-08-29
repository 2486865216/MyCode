package main

import(
	"fmt"
	"os"
)

func main(){
	fmt.Println("命令行参数", os.Args)
	//可以得到所有的命令行输入参数值
	for index,value := range os.Args {
		fmt.Printf("第%d个参数：%v\n", index, value)
	}
}