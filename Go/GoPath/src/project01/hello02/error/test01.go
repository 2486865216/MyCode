package main
/* 
1)Go语言追求简洁优雅，所以，Go语言不支持传统的try...catch..finally这种处理。
2)Go中引入的处理方式为：defer,panic,recover
3)这几个异常的使用场景可以这么简单描述：Go中可以抛出一个panic的异常，然后在defer中通过recover捕获这个异常，然后正常处理
*/
import(
	"fmt"
	"errors"
)

func test(){
	// defer func (){
	// 	err := recover() //recover()neiZhiFunction，可以捕获到异常
	// 	if err != nil {
	// 		fmt.Println("err = ", err)
	// 	}
	// }()

	defer func (){
		//recover()neiZhiFunction，可以捕获到异常
		if err := recover(); err != nil {
			fmt.Println("err = ", err)
		}
	}()

	x := 10
	y := 0
	fmt.Println("res = ", x / y)
}


/* 
自定义错误
Go程序中，也支持自定义错误，使用errors.New和panic内置函数。
1)errors.New("错误说明")，会返回一个error类型的值，表示一个错误
2)panic内置函数，接收一个interface类型的值（也就是任何值了）作为参数。可以接收error类型的变量，输出错误信息，并退出程序.
*/

//函数去读取以配置文件init.conf的信息
//如果文件名传入不正确，我们就返回一个自定义的错误
func readConf(name string) error{
	if name == "config.ini" {
		return nil
	}else {
		return errors.New("读取文件错误.......Hi")
	}
}

func test02(){
	err := readConf("config.ini")
	if err != nil {
		panic(err)
	}

	fmt.Println("success")
}

func main(){
	test()
	fmt.Println("code is here")

	test02()
}