package main

import (
	"fmt"
	"testing"
)

/*
testing提供对Go包的自动化测试的支持。通过'go test'命令，能够自动执行如下形式的任何函数：
func TestXxx(t *testing.T)
其中Xxx可以是任何字母数字字符串(但第一个字母不能是a-z)用于识别测试例程。
*/

func AddTest(n int) int {
	return n + n
}

func TestAdd(t *testing.T) {
	res := AddTest(5)
	if res != 10 {
		t.Fatalf("AddTest执行错误,期望值 %v,实际值 %v\n",10, res)
	}
	t.Logf("正确%v", res)
}

func main(){
	fmt.Println("ok")
}