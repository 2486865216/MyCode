package utils

import "fmt"

type FamilyAccount struct {
	//接受输入
	key string
	//是否退出
	loop bool
	//账户余额
	balance float64
	//每次的收支
	money float64
	//收支说明
	note string
	//收支详情
	flag    bool
	details string
}

//构造函数
func NewFamilyAccount() *FamilyAccount {
	return &FamilyAccount{
		key:     "",
		loop:    true,
		balance: 10000.0,
		money:   0.0,
		note:    "",
		details: "收支\t账户金额\t收支金额\t说  明",
	}
}

//显示主菜单
func (f *FamilyAccount) Main() {
	for {
		fmt.Println("====================家庭收支记账软件====================")
		fmt.Println("                      1.收支明细")
		fmt.Println("                      2.登记收入")
		fmt.Println("                      3.登记支出")
		fmt.Println("                      4.退出软件")

		fmt.Println()
		fmt.Println("请选择(1-4): ")
		fmt.Scanln(&f.key)

		switch f.key {
			case "1":
				f.showDetails()
			case "2":
				f.increment()
			case "3":
				f.decrement()
			case "4":
				f.exit()
			default:
				fmt.Println("请输入正确的选项")
		}
		if !f.loop {
			break
		}
	}
	fmt.Println("你退出了!")
}

//显示明细
func (f *FamilyAccount) showDetails() {
	fmt.Println("====================收支明细====================")
	if f.flag {
		fmt.Println(f.details)
	} else {
		fmt.Println("当前没有收支")
	}
}

//登记收入
func (f *FamilyAccount) increment() {
	fmt.Println("                      2.登记收入")
	fmt.Println("本次收入金额:")
	fmt.Scanln(&f.money)
	f.balance += f.money
	fmt.Println("本次收入说明:")
	fmt.Scanln(&f.note)
	f.details += fmt.Sprintf("\n收入\t%v\t\t%v\t\t%v", f.balance, f.money, f.note)
	f.flag = true
}

//登记支出
func (f *FamilyAccount) decrement() {
	fmt.Println("                      3.登记支出")
	fmt.Println("本次支出金额:")
	fmt.Scanln(&f.money)
	if f.money > f.balance {
		fmt.Println("余额不足")
		return
	}
	f.balance -= f.money
	fmt.Println("本次支出说明:")
	fmt.Scanln(&f.note)
	f.details += fmt.Sprintf("\n支出\t%v\t\t%v\t\t%v", f.balance, f.money, f.note)
	f.flag = true
}

//退出
func (f *FamilyAccount) exit() {
	fmt.Println("情定要退出码! y/n")

	sure := ""
	for {
		fmt.Scanln(&sure)
		if sure == "y" || sure == "n" {
			break
		}
		fmt.Println("请输入正确的选项 y/n")
	}
	if sure == "y" {
		f.loop = false
	}
}
