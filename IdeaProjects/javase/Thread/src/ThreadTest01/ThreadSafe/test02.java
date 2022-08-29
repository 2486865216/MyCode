package ThreadTest01.ThreadSafe;

public class test02 {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        Thread m1 = new MYclass(o1,o2);
        Thread m2 = new MYclass1(o1,o2);

        m1.start();
        m2.start();

    }
}
    class MYclass extends Thread{
        Object o1;
        Object o2;
        public MYclass(Object o1,Object o2){
            this.o1 = o1;
            this.o2 = o2;
        }
        public void run(){
            synchronized (o1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("hello");
                }
            }
        }
}
class MYclass1 extends Thread {
    Object o1;
    Object o2;

    public MYclass1(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public void run() {
        synchronized (o2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o1) {
                System.out.println("word");
            }
        }
    }
}