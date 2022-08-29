package main
import(
	"fmt"
	"math/rand"
	"time"
)
func main(){
	//为了每次生成的随机数不一样，我们需要给一个seed值
	rand.Seed(time.Now().UnixNano())

	fmt.Println(rand.Intn(100))
}