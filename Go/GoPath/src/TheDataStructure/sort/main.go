package sort

func Swap(array *[10]int, a, b int) {
	temp := array[a]
	array[a] = array[b]
	array[b] = temp
 }
