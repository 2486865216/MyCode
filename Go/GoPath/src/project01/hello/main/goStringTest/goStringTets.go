package main
import(
	"fmt"
	"strconv"
)

func main(){
	//string由字节组成
	var c1 byte = 'a'
	fmt.Printf("c1 = %d\n", c1)
	fmt.Printf("c1 = %c", c1)

	//字符串一旦赋值了，字符串就不能修改了：在G0中字符串是不可变的
	var string1 string = "test1"
	// string1[0] = 'a'
	var string2 string = `
		test
		hello go
	`
	fmt.Println("string1 = ", string1)
	fmt.Println("string2 = ", string2)

	//string分行拼接时要以加号结尾
	string3 := "hello " + "hello " + "hello " + "hello " +
	 "hello " + "hello " + "hello " + "hello "
	fmt.Println("string3 = ", string3)

	//类型转换
	var i int = 100
	var j int8 = int8(i)
	fmt.Println(j)

	//string转换
	var a int = 200
	var b bool = true
	var str string = fmt.Sprintf("%d", a)
	var str1 string = fmt.Sprintf("%t", b)
	fmt.Printf("str  = %q\n", str)
	fmt.Printf("str1 = %q\n", str1)

	var c float64 = 1.00
	var str2 string = strconv.FormatInt(int64(a), 10)
	var str3 string = strconv.FormatFloat(c, 'f', 10, 64)
	fmt.Printf("str2 = %q\n", str2)
	fmt.Printf("str3 = %q\n", str3)

	string4 := "100"
	var d int64
	d,_ = strconv.ParseInt(string4, 10, 64)
	fmt.Printf("d = %d", d)
}