package ThreadTest01;

public class test01 {
    public static void main(String[] args) {
        test t = new test();
        t.start();

        test1 t1 = new test1();
        t1.run();
    }
}
