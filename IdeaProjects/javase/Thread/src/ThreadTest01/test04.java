package ThreadTest01;

public class test04 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println(myThread.getName());

//        myThread.setName("hello");
//        System.out.println(myThread.getName());

        Thread t1 = Thread.currentThread();
        System.out.println(t1);
    }
}
class MyThread extends Thread{
    public void run(){
        System.out.println(Thread.currentThread()+"hello word!");
    }
}