package main

import "fmt"

func sort(arr *[]int){
	for i := len(*arr) - 1; i >= 0; i-- {
		for j := 0; j < i; j++ {
			if (*arr)[j] > (*arr)[j + 1] {
				temp := (*arr)[j]
				(*arr)[j] = (*arr)[j + 1]
				(*arr)[j + 1] = temp
			}
		}
	}
}

func main(){
	arr := []int{3, 5, 67, 3, 4, 6, 7, 8, 4, 2}
	sort(&arr)
	fmt.Println(arr)
}