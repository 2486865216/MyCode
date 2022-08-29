package main
/*
说明：如果我们起了一个协程，但是这个协程出现了panic,如果我们没有
捕获这个panic,就会造成整个程序崩溃，这时我们可以在goroutine中使用
recover来捕获panic,进行处理，这样即使这个协程发生的问题，但是主
线程仍然不受影响，可以继续执行。
*/
import(
	"fmt"
	"time"
)
func sayHello(){
	for i := 0; i < 10; i++ {
		time.Sleep(time.Second)
		fmt.Println("syHello ",i)
	}
}
func mapTest(){
	defer func(){
		err := recover()
		fmt.Println(err)
	}()
	var myMap map[int]string
	myMap[0] = "hello"
}
func main(){
	go sayHello()
	go mapTest()
	for i := 0; i < 10; i++ {
		time.Sleep(time.Second)
		fmt.Println("main ",i)
	}
}