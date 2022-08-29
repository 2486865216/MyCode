package main

import "fmt"
import "TheDataStructure/sort"

func selectSort(array *[10]int) {
	var min int
	for i := 0; i < 10 - 1; i++ {
		min = i
		for j := i + 1; j < 10; j++ {
			if array[j] < array[min] {
				min = j
			}
		}
		sort.Swap(array, i, min)
	}
}

func main() {
	array := [10]int{4, 6, 1, 3, 8, 7, 4, 2, 9, 6}
	selectSort(&array)
	fmt.Println(array)
}
