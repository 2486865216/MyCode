package ThreadTest01.ThreadSafe;

public class AccountThread extends Thread{
    private Accouont act;
    public AccountThread(){

    }
    public AccountThread(Accouont act){
        this.act = act;
    }
    public void run(){
        act.qukuan(5000);
        System.out.println(Thread.currentThread().getName()+":"+act.getId()+"取款成功：剩余"+act.getMoney());
    }
}
