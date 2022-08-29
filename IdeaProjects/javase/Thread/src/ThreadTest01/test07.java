package ThreadTest01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class test07 {
    public static void main(String[] args) {
        /*
            T线程在0对象上活动。
            T线程是当前线程对象。
            当调用o.wait()方法之后。
            T线程进入无期限等待。
            当前线程进入等待状态。
            直到最终调用o.notify()方法。
            o.notify0()方法的调用可以让正在0对象上等待的线程唤醒
            notifyAll()唤醒o对象上等待的所有线程
            ===================================
            1、使用wait方法和notify方法实现“生产者和消费者模式”
            2、什么是“生产者和消费者模式”？
                生产线程负责生产，消费线程负责消费。
                生产线程和消费线程要达到均衡。
            这是一种特殊的业务需求，在这种特殊的情况下需要使用wait方法和notify方法。
            3、wait和notify方法不是线程对象的方法，是普通java对象都有的方法。
            4、wait方法和notify方法建立在线程同步的基础之上。因为多线程要同时操作一个仓库。有线程安全问题。
            5、wait方法作用：o.wait()让正在o对象上活动的线程t进入等待状态，并且释放掉t线程之前占有的o对象的锁。
            6、notify方法作用：o.notify()让正在。对象上等待的线程唤醒，只是通知，不会释放。对象上之前占有的锁
        */
        List list = new ArrayList();
        Thread t1 = new Thread(new Producer(list));
        Thread t2 = new Thread(new Consumer(list));

        t1.setName("生产者");
        t2.setName("消费者");

        t1.start();
        t2.start();

    }
}
class Producer implements Runnable{
    private List list;
    public Producer(){

    }
    public Producer(List list){
        this.list = list;
    }
    @Override
    public void run() {
        while (true){
            synchronized (list){
                if (list.size()>0){
                    try {
                        System.out.println("生产者");
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object obj = new Object();
                list.add(obj);
                System.out.println(Thread.currentThread().getName()+"======>"+obj);
                list.notifyAll();
            }
        }
    }
}
class Consumer implements Runnable{
    private List list;
    public Consumer(){

    }
    public Consumer(List list){
        this.list = list;
    }
    @Override
    public void run() {
        while (true){
            synchronized (list){
                if (list.size()==0){
                    try {
                        System.out.println("消费者");
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object obj = list.remove(0);
                System.out.println(Thread.currentThread().getName()+"==========>"+obj);
                list.notifyAll();
            }
        }
    }
}
