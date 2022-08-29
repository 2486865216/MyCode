package main

import "fmt"

func insertSort(array *[10]int) {
	for i := 1; i < 10; i++ {
		temp := array[i]
		index := i
		for ;index > 0 && array[index - 1] > temp; index-- {
			array[index] = array[index - 1]
		}
		array[index] = temp
	}
}

func main() {
	array := [10]int{4, 6, 1, 3, 8, 7, 4, 2, 9, 0}
	insertSort(&array)
	fmt.Println(array)
}
