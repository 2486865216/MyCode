package main
import "fmt"

func test(arr [3]int) {
	fmt.Printf("%p\n", &arr)
}

func test02(arr *[3]int){
	(*arr)[0] = 100
	fmt.Println(arr)
}

func main(){
	arr := [...]int{1,2,3}
	fmt.Printf("%p\n", &arr)

	test(arr)

	test02(&arr)
}