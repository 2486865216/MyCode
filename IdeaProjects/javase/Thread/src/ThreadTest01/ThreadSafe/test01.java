package ThreadTest01.ThreadSafe;

public class test01 {
    public static void main(String[] args) {
        Accouont act = new Accouont("zhangsan",10000);
        Thread t1 = new AccountThread(act);
        Thread t2 = new AccountThread(act);

        t1.start();
        t2.start();
    }
}
