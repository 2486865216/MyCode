package main
import "fmt"

func main(){
	var a = 1
	var b = 2

	a = a + b
	b = a - b
	a = a - b

	fmt.Println("a = ", a)
	fmt.Println("b = ", b)
}