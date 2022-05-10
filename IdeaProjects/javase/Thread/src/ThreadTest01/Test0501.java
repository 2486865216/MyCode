package ThreadTest01;

public class Test0501 {
    public static void main(String[] args) {
//        中止线程睡眠
        Thread t = new Thread(new MyRunable());
        t.start();
        try {
            Thread.sleep(1000*3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();//干扰
    }
}
class MyRunable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"----->begin");

        try {
            Thread.sleep(1000*60*60*24*365);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"----->ends");
    }
}
