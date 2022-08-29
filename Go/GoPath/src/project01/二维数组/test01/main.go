package main
import "fmt"

func main(){
	var arr [5][5]int = [5][5]int{{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}}
	fmt.Printf("%T\n", arr)
	for i := 0; i < len(arr); i++ {
		fmt.Println(arr[i])
	}

	for index, value := range arr{
		fmt.Println(index)
		for _, value1 := range value {
			fmt.Printf("%v\t", value1)
		}
		fmt.Println()
	}
}