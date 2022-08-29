package main

import(
	"fmt"
)

func main(){
	// break语句出现在多层嵌套的语句块中时，可以通过标签指明要终止的是哪一层语句块
	// lable1:
	for i := 1; i < 5; i++ {
		label2:
		for j := 1; j < 5; j++ {
			if j == 2 {
				break label2
			}
			fmt.Println("j = ", j)
		}
	}
	// continue语句出现在多层嵌套的循环语句体中时，可以通过标签指明要跳过的是哪一层循环，这个和前面的标签的使用的规则一样.
}