package src.finalize;

public class neibulei {
    public static void main(String[] args) {
        test01 test011 = new test01();
        test011.sum(new test(){
            public int sum (int a,int b){
                return a+b;
            }
        },10,10);
    }
}
interface test{
    int sum(int a,int b);
}
class test01{
    public void sum(test t1,int a,int b){
        int value = t1.sum(a,b);
        System.out.println("a + b = "+value);
    }
}
