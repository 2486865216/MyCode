package main

import "fmt"

/* 
接口里的所有方法都没有方法体，即接口的方法都是没有实现的方法。接口体现了程序设计的多态和高内聚低偶合的思想。

Golang中的接口，不需要显式的实现。只要一个变量，含有接口类型中的所有方法，那么这个变量就实现这个接口。因此，Golang中没有implement?这样的关键字
 */

type Usb interface {
	Start()
	Stop()
}

type Phone struct {

}

func (p Phone) Start(){
	fmt.Println("手机开始工作")
}

func (p Phone) Stop(){
	fmt.Println("手机停止工作")
}

type Camera struct {

}

func (c Camera) Start(){
	fmt.Println("相机开始工作")
}

func (c Camera) Stop(){
	fmt.Println("相机停止工作")
}

type Computer struct {

}

//编写一个方法Working方法，接收一个Usb接口类型变量
func (c Computer) Working(usb Usb){
	usb.Start()
	usb.Stop()
}

func main(){
	phone := Phone{}
	camera := Camera{}
	computer := Computer{}


	computer.Working(phone)
	computer.Working(camera)
}

/* 
1)接口本身不能创建实例，但是可以指向一个实现了该接口的自定义类型的变量实例

2）接口中所有的方法都没有方法体，即都是没有实现的方法。

3)在Golang中，一个自定义类型需要将某个接口的所有方法都实现，我们说这个自定义类型实现了该接口。

4)一个自定义类型只有实现了某个接口，才能将该自定义类型的实例（变量）赋给接口类型。

5)只要是自定义数据类型，就可以实现接口，不仅仅是结构体类型。

6)一个自定义类型可以实现多个接口

7)Golang接口中不能有任何变量

8)一个接口（比如A接口）可以继承多个别的接口（比如B,C接口），这时如果要实现A接口，也必须将B,C接口的方法也全部实现。

9)interface类型默认是一个指针（引用类型），如果没有对interface初始化就使用，那么会输出nil

10)空接口interface{}没有任何方法，所以所有类型都实现了空接口
*/