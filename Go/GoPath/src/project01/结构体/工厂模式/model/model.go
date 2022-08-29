package model

type student struct {
	Name string
	Age int
}

func NewStudent(name string, age int) * student {
	return &student{
		Name: name,
		Age: age,
	}
}

//当其中的变量为小写时
func (s *student) GetName() string{
	return s.Name
}
