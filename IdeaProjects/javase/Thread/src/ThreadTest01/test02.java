package ThreadTest01;

public class test02 {
    public static void main(String[] args) {
        test1 te = new test1();
        Thread t = new Thread(te);
        t.start();
    }
}
