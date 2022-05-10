package ThreadTest01;

public class test03 {
    public static void main(String[] args) {
//        匿名内部类
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                }
            }
        });

        t.start();
    }
}
