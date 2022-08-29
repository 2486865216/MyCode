package com.example.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * author ye
 * createDate 2022/3/24  9:50
 */
class MyTask extends RecursiveTask<Integer>{

    //拆分差值不能超过10
    private static final int VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    //拆分和合并
    @Override
    protected Integer compute() {
        if ((end - begin) <= VALUE){
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else {
            int middle = (begin + end) / 2;

            MyTask myTask = new MyTask(begin, middle);
            MyTask myTask1 = new MyTask(middle + 1, end);

            //拆分
            myTask.fork();
            myTask1.fork();

            //合并
            result = myTask.join() + myTask1.join();
        }
        return result;
    }
}
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);
        //创建分支合并池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        Integer integer = submit.get();
        System.out.println(integer);

        //关闭
        forkJoinPool.shutdown();
    }
}
