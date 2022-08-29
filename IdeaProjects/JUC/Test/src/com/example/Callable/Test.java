package com.example.Callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * author ye
 * createDate 2022/3/23  13:19
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunableTest RunTest = new RunableTest();
        CallableTest callTest = new CallableTest();

        //RunnabLe接口创建线程
        new Thread(RunTest, "AA").start();
        //Callable接口创建线程
        //new Thread(callTest, "BB").start(); //报错


        //FutureTask
        //FutureTask<Integer> futureTask = new FutureTask(new CallableTest());

        FutureTask<Integer> futureTask = new FutureTask(() -> {
            System.out.println(Thread.currentThread().getName() + "come in callable");
            return 1024;
        });

        new Thread(futureTask, "call").start();

        while (!futureTask.isDone()){
            System.out.println("wait...");
        }

        System.out.println(futureTask.get());

        System.out.println(Thread.currentThread().getName() + "over");
    }
    //FutureTask,原理未来任务
/**
 *1、
 老师上课，口渴了，去买票不合适，讲课线程继续。
 单开启线程找班上班长帮我买水，把水买回来，需要时候直接t

 *2、4个同学，1同学1+2..5 ,2同学10+11+12..50, 3同学60+61+62, 4同学100+200
 第2个同学计算量比较大，
 FutureTask单开启线程给2同学计算，先汇总134，最后等2同学计算位完成，统一汇总

 *3、考试，做会做的题目，最后看不会做的题目
 */
}
