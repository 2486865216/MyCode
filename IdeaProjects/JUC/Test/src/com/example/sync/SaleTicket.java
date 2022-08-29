package com.example.sync;

/**
 * author ye
 * createDate 2022/3/21  13:33
 *
 * //第一步创建资源类，定义属性和和燥作方法
 */
class Ticket{
    //票数
    private int number = 30;
    //买票
    public synchronized void sale(){
        //判断是否有票
        if (number > 0){
            System.out.println(Thread.currentThread().getName() + " : 卖出1, 剩下:" + number--);
        }
    }
}
public class SaleTicket {
    //第二步创建多个线程，调用资源类的燥作方法
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"CC").start();
    }
}
