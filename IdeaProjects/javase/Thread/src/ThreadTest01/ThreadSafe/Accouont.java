package ThreadTest01.ThreadSafe;

public class Accouont {
    private String id;
    private int money;

    public Accouont(){

    }

    public Accouont(String id,int money){
        this.id = id;
        this.money = money;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public String getId() {
        return id;
    }
    public void qukuan (int hoemoney) {
        synchronized (this){
            int before = getMoney();
            int after = before - hoemoney;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setMoney(after);
        }
    }
}
