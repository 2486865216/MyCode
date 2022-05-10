package com.example.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author ye
 * createDate 2022/3/21  19:47
 */
class Ticket{
    private int number = 30;

    private final Lock lock = new ReentrantLock();

    public void sale(){
        //上锁
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName() + " : 卖出1, 剩下:" + number--);
            }
        } finally {
            //解锁
            lock.unlock();
        }
    }
}
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"AA").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"CC").start();
    }
}
