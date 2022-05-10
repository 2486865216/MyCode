package Lambda;

import org.junit.Test;

import java.util.Comparator;

public class LambdaTest1 {
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Word!");
            }
        };
        r1.run();
        System.out.println("=======================");
        Runnable r2 = () -> System.out.println("Hello Word!");
    }

    @Test
    public void test2() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compara1 = com1.compare(12, 21);
        System.out.println(compara1);
        System.out.println("=======================");

        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        int compara2 = com2.compare(12, 21);
        System.out.println(compara1);
        System.out.println("=======================");

        //方法引用
        Comparator<Integer> com3 = Integer::compare;
        int compara3 = com3.compare(32, 21);
        System.out.println(compara3);
    }
}
