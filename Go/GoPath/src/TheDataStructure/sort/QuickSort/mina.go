package main

import "fmt"

func quickSort(array []int, left, right int) {
	if left >= right {
		return
	}
	temp := array[left]
	start := left
	stop := right

	for left != right {
		for array[right] >= temp && left < right {
			right--
		}
		for array[left] <= temp && left < right {
			left++
		}
		if left < right {
			array[left], array[right] = array[right], array[left]
		}
	}
	array[start], array[right] = array[right], temp
	quickSort(array, start, left)
	quickSort(array, right + 1, stop)
}

func main() {
	array := [10]int{4, 6, 1, 3, 8, 7, 4, 2, 9, 0}
	quickSort(array[:], 0, len(array) - 1)
	fmt.Println(array)
}

