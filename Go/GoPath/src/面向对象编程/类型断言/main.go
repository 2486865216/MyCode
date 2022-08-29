package main
import "fmt"

type Point struct {
	x int
	y int
}

func main(){
	var a interface{}
	var point = Point{1, 2}
	a = point
	//断言
	var b = a.(Point)
	fmt.Println(a)
	fmt.Println(b)

	var c interface{}
	var d float32 = 1.1
	c = d
	e, ok := c.(float64)

	if ok {
		fmt.Println(e)
	} else {
		fmt.Println("转换失败")
	}
}