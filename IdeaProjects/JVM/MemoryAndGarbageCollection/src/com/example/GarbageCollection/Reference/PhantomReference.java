package com.example.GarbageCollection.Reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * author ye
 * createDate 2022/2/16  12:37
 */
public class PhantomReference {
    public static PhantomReference obj;
    static ReferenceQueue<PhantomReference> phantomReferenceReferenceQueue = null;//引用队列
    public static class CheckRefQueue extends Thread{
        @Override
        public void run() {
            while (true){
                if (phantomReferenceReferenceQueue != null){
                    Reference<PhantomReference> refs = null;
                    try{
                        refs = (Reference<PhantomReference>) phantomReferenceReferenceQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (refs != null){
                        System.out.println("追踪垃圾回收过程,PhantomReference实例被GC了");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类的finalize方法");
        obj = this;
    }

    public static void main(String[] args) {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);//设置守护线程设置为守护线程:当程序中没有非守护线程时,守护线程也就执行结束
        t.start();

        phantomReferenceReferenceQueue = new ReferenceQueue<>();
        obj = new PhantomReference();
        //虚引用
        Reference<PhantomReference> ref = new java.lang.ref.PhantomReference<>(obj, phantomReferenceReferenceQueue);
         try {
             System.out.println(ref.get());//null
             //去除强引用
             obj = null;
             //第一次进行GC,出于对象可复活,GC无法回收该对象
             System.gc();
             Thread.sleep(1000);
             if (obj == null){
                 System.out.println("obj is null");
             }else {
                 System.out.println("obj 可用 ");
             }
             System.out.println("第二次GC");
             obj = null;
             System.gc();
             Thread.sleep(1000);
             if (obj == null){
                 System.out.println("obj is null");
             }else {
                 System.out.println("obj 可用 ");
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
}
