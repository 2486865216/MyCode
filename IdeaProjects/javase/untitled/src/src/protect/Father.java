package src.protect;


public class Father {
    public static void main(String[] args) {
        Father father = new Father();
        father.test();
        father.Father1();

        Father2 father2 = new Father2();
        father2.test();
        father2.Father1();
    }
    protected void test(){
        System.out.println("hello word test!");
    }
    public void Father1(){
        System.out.println("hello word Father!");
    }
}
