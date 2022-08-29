package main

import (
	"fmt"
)

type ValNode struct {
	row int
	col int
	val int
}

func main() {
	var array [10][10]int
	array[1][2] = 1
	array[2][3] = 2
	for _, value := range array {
		for _, value1 := range value {
			fmt.Printf("%d ", value1)
		}
		fmt.Println()
	}

	var sparseArray []ValNode
	//第一行记录数据的大小以及其它的值
	sparseArray = append(sparseArray, ValNode{
		10,
		10,
		0,
	})
	for i, value := range array {
		for j, value1 := range value {
			if value1 != 0 {
				valNode := ValNode{
					row: i,
					col: j,
					val: value1,
				}
				sparseArray = append(sparseArray, valNode)
			}
		}
	}
	//输出稀疏数组
	for i, val := range sparseArray {
		fmt.Printf("%d : %d %d %d \n",i, val.row, val.col, val.val)
	}

	var array2 [10][10]int
	//遍历sparseArray
	for i, val := range sparseArray {
		if i != 0 {
			array2[val.row][val.col] = val.val
		}
	}

	fmt.Println("恢复之后的数组")
	for _, value := range array2 {
		for _, value1 := range value {
			fmt.Printf("%d ", value1)
		}
		fmt.Println()
	}
}
