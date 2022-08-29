package main
import(
	"fmt"
	"time"
)

/* const(
	Nanosecond  Duration=1 //纳秒
	Microsecond = 1000*Nanosecond //微秒
	Millisecond = 1000*Microsecond //毫秒
	Second = 1000*Millisecond //秒
	Minute = 60*Second //分钟
	Hour = 60*Minute //小时
) */

func main(){
	now := time.Now()
	fmt.Printf("类型 = %T, now = %v\n", now, now)
	fmt.Println("year = ", now.Year())
	fmt.Println("month = ", now.Month())
	fmt.Println("day = ", now.Day())
	fmt.Println("hour = ", now.Hour())
	fmt.Println("minute = ", now.Minute())
	fmt.Println("second = ", now.Second())

	//格式化日期
	fmt.Printf("当前年月日: %d-%d-%d  %d-%d-%d\n", now.Year(), now.Month(), now.Day(), now.Hour(), now.Minute(), now.Second())


	fmt.Println(now.Format("2006-01-02 15:04:05"))
	fmt.Println(now.Format("2006-01-02"))
	fmt.Println(now.Format("15:04:05"))

	// for i := 0; i < 10; i++ {
	// 	time.Sleep(time.Second)
	// 	fmt.Println(i)
	// }

	fmt.Printf("Unix的时间 = %v, UnixNano的时间 = %v\n", now.Unix(), now.UnixNano())
}