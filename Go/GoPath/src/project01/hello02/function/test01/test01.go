package test01
import "fmt"


func Cal(a int, b int, opreater byte) int {
	var res int
	switch opreater {
		case '+' :
			res = a + b
		case '-' :
			res = a - b
		case '*' :
			res = a * b
		case '/' :
			res = a / b
		default :
			fmt.Println("传参错误!")
	}
	return res
}