package main
import(
	"fmt"
)
/* 
在函数中，程序员经常需要创建资源（比如：数据库连接、文件句柄、锁等），为了在函数执行完毕后，及时的释放资源，

Go的设计者提供defer(延时机制)。
*/

func sum(n1, n2 int) int {
	//将defer后的语句压入栈中
	//当函数执行完毕后在执行，先入后出
	//在defer将语句放入到栈时，也会将相关的值拷贝同时入栈。
	defer fmt.Println("ok n1 = ", n1)
	defer fmt.Println("ok n2 = ", n2)

	n1++
	n2++

	res := n1 + n2
	fmt.Println("ok res = ", res)
	return res;
}

func main(){
	sum(10, 20)
}