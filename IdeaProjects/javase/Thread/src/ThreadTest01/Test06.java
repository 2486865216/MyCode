package ThreadTest01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test06 {
    public static void main(String[] args) {
        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(1000);
                int a = 100;
                int b = 200;
                return a+b;
            }
        });

        Thread t1 = new Thread(task);
        t1.start();
        try {
            Object obj = task.get();
//            get方法执行会导致当前线程阻塞
            System.out.println(obj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
