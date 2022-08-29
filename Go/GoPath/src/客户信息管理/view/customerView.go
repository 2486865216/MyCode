package main

import (
	"fmt"
	"客户信息管理/model"
	"客户信息管理/service"
)

type customerView struct {
	//接受用户输入
	key  string
	loop bool

	customerService service.CustomerService
}

//显示主菜单
func (cv *customerView) mainMenu() {
	for {
		fmt.Println("==========客户信息管理==========")
		fmt.Println("	  1.添 加 用 户")
		fmt.Println("	  2.修 改 用 户")
		fmt.Println("	  3.删 除 用 户")
		fmt.Println("	  4.客 户 列 表")
		fmt.Println("	  5.退 出 软 件")
		fmt.Println("请选择(1 - 5):")
		fmt.Scanln(&cv.key)
		switch cv.key {
		case "1":
			cv.add()
		case "2":
			cv.updateUser()
		case "3":
			cv.deleteUser()
		case "4":
			cv.list()
		case "5":
			fmt.Println("确认退出吗？(y/n)")
			choice := ""
			fmt.Scanln(&choice)
			for {
				if choice == "y" || choice == "Y" || choice == "n" || choice == "N" {
					break
				}
				fmt.Println("请重新输入(y/n)")
				fmt.Scanln(&choice)
			}
			if choice == "y" || choice == "Y" {
				cv.loop = false
			}
		default:
			fmt.Println("输入有误,请重新输入")
		}
		if !cv.loop {
			break
		}
	}
	fmt.Println("已退出!")
}

//显示所有的用户信息
func (v *customerView) list() {
	customers := v.customerService.GetCustomers()
	fmt.Println("==========客 户 列 表==========")
	fmt.Println("编号\t姓名\t性别\t年龄\t电话\t邮箱")
	for i := 0; i < len(customers); i++ {
		fmt.Println(customers[i].GetInfo())
	}
}

//添加用户
func (c *customerView) add() {
	fmt.Println("----------添 加 用 户----------")
	name := ""
	gender := ""
	age := 0
	phone := ""
	email := ""
	fmt.Println("姓名:")
	fmt.Scanln(&name)
	fmt.Println("性别:")
	fmt.Scanln(&gender)
	fmt.Println("年龄:")
	fmt.Scanln(&age)
	fmt.Println("电话:")
	fmt.Scanln(&phone)
	fmt.Println("邮箱:")
	fmt.Scanln(&email)

	customer := model.NewCustomer2(name, gender, age, phone, email)
	c.customerService.Add(customer)
	fmt.Println("----------添加成功----------")
}

//删除用户
func (cv *customerView) deleteUser() {
	fmt.Println("----------删除用户----------")
	fmt.Println("请选择要删除的用户id(-1退出):")
	var id int
	fmt.Scanln(&id)
	if id == -1 {
		return
	}
	fmt.Println("确认删除吗？(y/n)")
	choice := ""
	fmt.Scanln(&choice)
	for {
		if choice == "y" || choice == "Y" || choice == "n" || choice == "N" {
			break
		}
		fmt.Println("请重新输入(y/n)")
		fmt.Scanln(&choice)
	}
	if choice == "y" || choice == "Y" {
		success := cv.customerService.DeleteById(id)
		if success {
			fmt.Println("----------删除成功!----------")
		} else {
			fmt.Println("----------删除失败!----------")
		}
	} else {
		return
	}
}

//修改用户
func (c *customerView) updateUser() {
	fmt.Println("----------修改用户----------")
	fmt.Println("请选择要修改的用户id(-1退出):")
	var id int
	fmt.Scanln(&id)
	if id == -1 {
		return
	}
	index := c.customerService.FindById(id)
	if index == -1 {
		fmt.Println("无此用户!")
		return
	}
	var name string
	var gender string
	var age int
	var phone string
	var email string
	customer := c.customerService.GetCustomers()[index]
	fmt.Println("用户名("+customer.Name+"):")
	fmt.Scanln(&name)
	fmt.Println("性别("+customer.Gender+"):")
	fmt.Scanln( &gender)
	ageStr := fmt.Sprintf("%d", customer.Age)
	fmt.Println("年龄("+ageStr+"):")
	fmt.Scanln(&age)
	fmt.Println("电话("+customer.Phone+"):")
	fmt.Scanln(&phone)
	fmt.Println("邮箱("+customer.Email+"):")
	fmt.Scanln(&email)

	fmt.Println("确认修改吗？(y/n)")
	choice := ""
	fmt.Scanln(&choice)
	for {
		if choice == "y" || choice == "Y" || choice == "n" || choice == "N" {
			break
		}
		fmt.Println("请重新输入(y/n)")
		fmt.Scanln(&choice)
	}

	if choice == "y" || choice == "Y" {

		success := c.customerService.UpdateUser(id, name, gender, age, phone, email)
		if success {
			fmt.Println("----------修改成功!----------")
		} else {
			fmt.Println("----------修改失败!----------")
		}
	} else {
		return
	}
}

func main() {
	view := customerView{
		key:  "",
		loop: true,
	}

	view.customerService = *service.NewCustomService()

	view.mainMenu()
}
