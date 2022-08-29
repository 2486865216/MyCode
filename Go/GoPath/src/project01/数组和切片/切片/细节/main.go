package main

/* 
切片初始化时var slice=arr[startIndex:endIndex]
说明：从arr数组下标为startIndex,取到下标为endIndex的元素（不含arr[endIndex]).

切片初始化时，仍然不能越界。范围在[o-len(arr)]之间，但是可以动态增长.

var slice = arr[O:end]可以简写var slice = arr[:end]
var slice = arr[start:len(arr)]可以简写：var slice = arr[start:]
var slice = arr[O:len(arr)]可以简写：var slice = arr[:]

cap是一个内置函数，用于统计切片的容量，即最大可以存放多少个元素。

切片定义完后，还不能使用，因为本身是一个空的，需要让其引用到一个数组，或者make一个空间供切片来使用

切片可以继续切片


用append内置函数，可以对切片进行动态追加

切片append操作的底层原理分析：
1)切片append:操作的本质就是对数组扩容
2)go底层会创建一下新的数组newArr(安装扩容后大小)
3)将slice,原来包含的元素拷贝到新的数组newArr
4)slice重新引用到newArr
5)注意newArr是在底层来维护的，程序员不可见.


copy拷贝切片

*/
import "fmt"
func main(){
	//append
	var slice []int = make([]int, 9, 10)

	slice = append(slice, 10)
	slice = append(slice, 10, 20)

	var slice2 []int = []int{1, 2, 3, 4, 5}
	fmt.Printf("%T\n", slice2)

	slice = append(slice, slice2...)

	fmt.Println("slice = ", slice)
	fmt.Println("slice size = ", len(slice))
	fmt.Println("slice cap =  ", cap(slice))

	//copy
	var slice3 []int = []int{1, 2, 3, 4, 5}
	var slice4 []int = make([]int, 10)
	copy(slice4, slice3)
	fmt.Println(slice3)
	fmt.Println(slice4)
}