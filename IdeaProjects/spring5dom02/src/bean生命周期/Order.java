package bean生命周期;

public class Order {
    public Order() {
        System.out.println("第一步");
    }

    private String name;

    public void setName(String name) {
        System.out.println("第二步");
        this.name = name;
    }

    public void initMethods(){
        System.out.println("第三步,执行初始化方法");
    }

    public void endMethods(){
        System.out.println("第五步,执行销毁方法");
    }
}
