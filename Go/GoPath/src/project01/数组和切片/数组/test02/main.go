package main

import(
	"fmt"
)

func main(){
	// var arr [5]int

	// for i := 0; i < len(arr); i++ {
	// 	fmt.Printf("请输入第%d个数:\n", i + 1)
	// 	fmt.Scanf("%d\n", &arr[i])
	// }

	// fmt.Println(arr)

	//四种初始化数组的方式

	var arr1 [3]int = [3]int{1,2,3}
	fmt.Println(arr1)
	fmt.Printf("%T\n", arr1)

	var arr2 = [3]int{1,2,3}
	fmt.Println(arr2)
	fmt.Printf("%T\n", arr2)

	var arr3 = [...]int{1,2,3,4,5,6,7}
	fmt.Println(arr3)
	fmt.Printf("%T\n", arr3)

	var arr4 = [...]int{1:1, 3:897, 7:78967}
	fmt.Println(arr4)
	fmt.Printf("%T\n", arr4)
}