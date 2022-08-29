package main

import(
	"fmt"
)

func main(){
	intChan := make(chan int, 3)
	intChan <- 100
	intChan <- 200
	intChan <- 300
	close(intChan)
	n1 := <- intChan
	fmt.Println(n1)

	intChan2 := make(chan int, 100)
	for i := 0; i< 100; i++ {
		intChan2 <- i
	}
	close(intChan2)
	for value := range intChan2 {
		fmt.Println(value)
	}
}