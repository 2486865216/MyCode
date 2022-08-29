package ThreadTest01;

public class shouhu {
    public static void main(String[] args) {
        Thread t1 = new testshouhu();
        t1.setDaemon(true);
        t1.start();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("------------------>"+(i+1));
        }
    }
}
class testshouhu extends Thread{
    public void run(){
        int i = 0;
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("===========>"+(++i));
        }
    }
}
