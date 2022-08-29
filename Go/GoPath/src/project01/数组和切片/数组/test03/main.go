package main
import(
	"fmt"
)
//遍历数组
func main(){
	arr :=  [...]int{1,2,3,4,5,453,657,432,645}

	for index,value := range arr {
		fmt.Printf("下标(index)%d, 值(value)%d\n", index, value)
	}
}