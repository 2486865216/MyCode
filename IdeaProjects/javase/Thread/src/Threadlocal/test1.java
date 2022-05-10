package Threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
* Threadlocal
* 1,Threadlocal可以为当前线程关联一个数据
* 2.每一个Threadloca对象，只能为当前线程关联一个数据，如果要为当前线程关联多个数据，就需要多个Threadloca对象实例
* 3.每个Thread local对象定义实例的时候，一般是static类型
* 4.Thread local中保存的数据，在线程销毁之后，会由JVM虚拟机自动释放*/
public class test1 {
    private static Map<String,Object> data = new HashMap<>();
    private static Random random = new Random();
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    public static class task implements Runnable{

        @Override
        public void run() {
            Integer i = random.nextInt(1000);
            String name = Thread.currentThread().getName();
            System.out.println("线程["+name+"]生成的随机数是："+i);
//            data.put(name,i);
            threadLocal.set(i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("线程【"+name+"】结束："+data.get(name));
            System.out.println("线程【"+name+"】结束："+threadLocal.get());

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new task()).start();
        }
    }
}
