package test02

import (
	"bufio"
	"encoding/json"
	"fmt"
	"io"
	"os"
)

type Monster struct {
	Name string
	Age int
}

func (m *Monster) Store() bool {
	data, err := json.Marshal(m)
	if err != nil {
		fmt.Println("json err = ", err)
		return false
	}
	filePath := "./text.txt"
	file, err2 := os.OpenFile(filePath, os.O_WRONLY | os.O_CREATE, 6666)
	if err2 != nil {
		fmt.Println("open file error = ", err2)
		return false
	}
	writer := bufio.NewWriter(file)
	_, err3 := writer.WriteString(string(data))
	writer.Flush()
	if err3 != nil {
		fmt.Println("write file err", err3)
		return false
	}
	return true
}
func (m *Monster) UnStore() bool {
	filePath := "./text.txt"
	file, err2 := os.Open(filePath)
	if err2 != nil {
		fmt.Println("open file error = ", err2)
		return false
	}
	reader := bufio.NewReader(file)
	for {
		str, err3 := reader.ReadString('\n')
		if err3 ==io.EOF {
			break
		}
		err := json.Unmarshal([]byte(str), &m)
		if err != nil {
			fmt.Println("unMarshal fail", err)
			return false
		}
	}
	return true
}