package com.example.sync02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author ye
 * createDate 2022/3/23  12:02
 */
class Phone{
    public static synchronized void sendSMS() throws InterruptedException {
        //停留四秒
        Thread.sleep(4000);
        System.out.println("sendSMS");
    }
    public synchronized void sendEmail(){
        System.out.println("sendEmail");
    }
    public void getHello(){
        System.out.println("getHello");
    }
}

/**
 * 8锁
 *
 * 1 标准访问，先打印短信还是邮件
 *      sendSMS
 *      sendEmail
 * 2 停4秒在短后方法内，先打印短信还是邮件
 *      sendSMS
 *      sendEmail
 * 3 新增普通TheLLo.方法，是先打短信还是he llo
 *      getHello
 *      sendSMS
 * 4 现在有两部手机，先打印短信还是邮件
 *      sendEmail
 *      sendSMS
 * 5 两个静态同步方法，1部手机，先打印短信还是邮件
 *      sendSMS
 *      sendEmail
 * 6两个静态同步方法，2部手机，先打印短信还是邮件
 *      sendSMS
 *      sendEmail
 * 7 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 *      sendEmail
 *      sendSMS
 * 8 1个静态同步方法，1个普通同步方法，2部手机，先打印短后还是邮件
 *      sendEmail
 *      sendSMS
 */
public class Lock_8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            //phone.sendEmail();
            //phone.getHello();
            phone2.sendEmail();
        },"BB").start();
    }
}
