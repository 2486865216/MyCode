package ThreadTest01;

public class test0502 {
//    终止一个线程
    public static void main(String[] args) {
        MyRunable01 r = new MyRunable01();
            Thread t = new Thread(r);
            t.setName("t");
            t.start();

        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        t.stop();
        r.run = false;
    }
}
class MyRunable01 implements Runnable{

    boolean run = true;
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            if (run){
                System.out.println(Thread.currentThread().getName()+"---->"+i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                return;
            }
        }
    }
}