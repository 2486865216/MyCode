package src.finalize;

public class finalize {
    public static void main(String[] args) {
        for (int i = 0;i<1000;i++){
            Test test = new Test();
            test = null;
            System.gc();
        }
    }
}
class Test{
    protected void finalize() throws Throwable{
        System.out.println("回收垃圾!");
    }
}
