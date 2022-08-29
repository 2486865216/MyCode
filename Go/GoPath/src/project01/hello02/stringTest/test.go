/* 
字符串中常用的系统函数

说明：字符串在我们程序开发中，使用的是非常多的

1)统计字符串的长度，按字节len(str)

2)字符串遍历，同时处理有中文的问题r := []rune(str)

3)字符串转整数：n,err := strconv.Atoi("12")

4)整数转字符串  str = strcony.Itoa(12345)

5)字符串转[]byte:var bytes = []byte("hello go")

6)[]byte转字符串：str = string([]byte{97,98,99})

7)10进制转2,8,16进制：str=strcony.Formatint(123,2)/∥2->8,16

8)查找子串是否在指定的字符串中：strings.Contains("seafood","foo")//true

9)统计一个字符串有几个指定的子串：strings.Count("ceheese'","e")//4

10)不区分大小写的字符串比较(e=是区分字母大小写的)：fmt.Println(strings.EqualFold("abc","Abc") //true

11)返回子串在字符串第一次出现的index值，如果没有返回-1：strings.Index("NLT_abc'","abc")//4

12)返回子串在字符串最后一次出现的index,如设有返回-1：strings.LastIndex("go golang'","go")

13)将指定的子串替换成另外一个子串：strings.Replace("go go hello", "go”, "go语言", n)n可以指定你希望替换几个，如果n=-1表示全部替换

14)按照指定的某个字符，为分割标识，将一个字符串拆分成字符串数组：strings.Split("hello,wrold,ok", ",")

15)将字符串的字母进行大小写的转换：strings.ToLower("Go")∥go strings.ToUpper("Go")/∥G0

l6)将字符串左右两边的空格去掉：strings.TrimSpace("tn a lone gopher ntrn")

17)将字符串左右两边指定的字符去掉：strings.Trim("hello!","!") //["hello"]  //将左右两边！和""去掉
18)将字符串左边指定的字符去掉：strings.TrimLeft("!hello!","!") /∥["hello!"]/∥将左边！和""去掉
19)将字符串右边指定的字符去掉：strings.TrimRight("!hello!","!")//["!hello"] //将右边！和""去掉
*/
package main
import (
	"fmt"
	"strings"
)
func main(){
	fmt.Println(strings.TrimRight("!hello!", "!"))
}