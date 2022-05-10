package com.example.GarbageCollection.FinalizationTest;

/**
 * author ye
 * createDate 2022/2/15  13:52
 * 测试对象的finalization机制
 */
public class CanReliveObject {
    public static CanReliveObject obj;//类变量，属于GC Root

    //此方法只能被调用一次
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类重写的finalize()方法");
        obj = this;//与引用链上的任何一个对象建立了联系
    }

    public static void main(String[] args) {
        try {
            obj = new CanReliveObject();
            obj = null;
            System.gc();
            System.out.println("第一次GC");
            //finalize线程优先级很低，暂停两秒以等待它
            Thread.sleep(2000);
            if (obj == null){
                System.out.println("obj is dead");
            }
            else {
                System.out.println("obj still alive");
            }
            System.out.println("第二次GC");
            obj = null;
            System.gc();
            if (obj == null){
                System.out.println("obj is dead");
            }
            else {
                System.out.println("obj still alive");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
