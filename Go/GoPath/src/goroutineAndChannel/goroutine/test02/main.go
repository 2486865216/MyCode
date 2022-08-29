package main
import (
	"fmt"
	"runtime"
)

func main(){
	//g01.8后，默认让程序运行在多个核上，可以不用设置了
	//go1.8前，还是要设置一下，可以更高效的利益cpu

	//系统的cpu数量
	num := runtime.NumCPU()
	fmt.Println(num)
	//设置使用多少俄国cpu
	runtime.GOMAXPROCS(num)
}