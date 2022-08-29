package service

import (
	"客户信息管理/model"
)

type CustomerService struct {
	customers []model.Customer
	//客户数量
	customerNum int
}

func NewCustomService() *CustomerService {
	service := CustomerService{}
	service.customerNum = 1
	user := model.NewCustomer(1, "hello", "男", 18, "110", "123@qq.com")
	service.customers = append(service.customers, user)
	return &service
}

//返回客户接片
func (c *CustomerService) GetCustomers() []model.Customer {
	return c.customers
}

//添加用户
func (c *CustomerService) Add(customer model.Customer) {
	c.customerNum++
	customer.Id = c.customerNum
	c.customers = append(c.customers, customer)
}

//根据id查找
func (c *CustomerService) FindById(id int) int {
	for index, value := range c.customers {
		if value.Id == id {
			return index
		}
	}
	return -1
}

//根据id删除
func (c *CustomerService) DeleteById(id int) bool {
	index := c.FindById(id)
	if index == -1 {
		return false
	}

	c.customers = append(c.customers[:index], c.customers[index+1:]...)

	return true
}

//根据id修改
func (c *CustomerService) UpdateUser(id int, name string, gender string, age int, phone string, email string) bool {
	index := c.FindById(id)
	if index == -1 {
		return false
	}

	c.customers[index].Name = name
	c.customers[index].Gender = gender
	c.customers[index].Age = age
	c.customers[index].Phone = phone
	c.customers[index].Email = email

	return true
}
