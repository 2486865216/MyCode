package main

import "fmt"
import "TheDataStructure/sort"

func bubbleSort(array *[10]int) {
	fmt.Println(*array)
	for i := 1; i < 10; i++ {
		for j := 0; j < 10 - i; j++ {
			if array[j] > array[j + 1] {
				sort.Swap(array, j, j + 1)
			}
		}
	}
}

func main() {
	array := [10]int{4, 6, 1, 3, 8, 7, 4, 2, 9, 6}
	fmt.Println(array)
	bubbleSort(&array)
	fmt.Println(array)
}
