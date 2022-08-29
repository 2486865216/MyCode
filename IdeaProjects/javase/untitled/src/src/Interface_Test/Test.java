package src.Interface_Test;

interface Math_1 {
    int i = 100;
    int sum(int a,int b);
}

public class Test {
    public static void main(String[] args){
       Test_1 test_1 = new Test_1();
        System.out.println(test_1.sum(5,5));

        Math_1 math_1 = new Test_1();
        System.err.println(math_1.sum(4,5));
    }
}

class Test_1 implements Math_1{
    public int sum(int a, int b){
        System.out.println(i);
        return a+b;
    }
}