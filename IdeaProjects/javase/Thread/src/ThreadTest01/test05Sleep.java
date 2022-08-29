package ThreadTest01;

public class test05Sleep {
    public static void main(String[] args) {
        /*public static void sleep(long millis)
                throws InterruptedException使当前正在执行的线程以指定的毫秒数暂停（暂时停止执行），
                具体取决于系统定时器和调度程序的精度和准确性。 线程不会丢失任何显示器的所有权。
                参数
                millis - 以毫秒为单位的睡眠时间长度
                异常
                IllegalArgumentException - 如果 millis值为负数
                InterruptedException - 如果任何线程中断当前线程。 当抛出此异常时，当前线程的中断状态将被清除。
        */
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(3*1000);
                System.out.println("word!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}