/* 
返回的是一个匿名函数.但是这个匿名函数引用到函数外的n.因此这个匿名函数就和形成一个整体，构成闭包。

大家可以这样理解：闭包是类。函数是操作，n是字段。函数和它使用到n构成闭包。

我们反复的调用f函数时，因为是初始化一次，因此每调用一次就进行累计。

我们要搞清楚闭包的关键，就是要分析出返回的函数它使用（引用）到哪些变量，因为函数和它引用到的变量共同构成闭包。 
*/
package main

import (
	"fmt"
	"strings"
)

func AddUpdate () func (int) int {
	var n = 10
	return func (x int) int {
		n = n + x
		return n
	}
}
/* 
编写一个函数makeSuffix(suffix string)可以接收一个文件后缀名（比如.jpg),并返回一个闭包

调用闭包，可以传入一个文件名，如果该文件名没有指定的后缀（比如jg),则返回文件名jpg,如果已经有.jpg后缀，则返回原文件名。

要求使用闭包的方式完成

strings.HasSuffix:函数可以判断某个字符串是否有指定的后缀
 */

 func makeSuffix (suffix string) func (string) string {
	 return func (name string) string {
		 if !strings.HasSuffix(name, suffix) {
			return name + suffix
		 }
		 return name
	 }
 }

func main(){
	f := AddUpdate()
	fmt.Println(f(1))
	fmt.Println(f(1))
	fmt.Println(f(3))

	makeSuffixTest := makeSuffix(".jpg")
	fmt.Printf("文件名处理后 = %v\n", makeSuffixTest("test"))
	fmt.Printf("文件名处理后 = %v\n", makeSuffixTest("test.jpg"))
}