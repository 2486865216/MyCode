package src.Thread;

public class Thread_test {
    public static void main(String[] args){
        Runner runner = new Runner();
        Thread thread = new Thread(runner);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
