package Lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class LambdaTest2 {
    //一、无参数、无返回值
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        Runnable r2 = () -> System.out.println("word");
    }

    //二、有参数、无输出
    @Test
    public void test2() {
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("hello2");
        Consumer<String> con2 = (String s) -> System.out.println(s);
        con2.accept("hello2-1");
    }

    //三、数据类型可以省略，因为可由编译器推断得出，称为”类型推断“
    @Test
    public void test3() {
        Consumer<String> con2 = (s) -> System.out.println(s);
        con2.accept("hello3");
    }

    //四、若只有一个参数时，小括号可以省略
    @Test
    public void test4() {
        Consumer<String> con2 = s -> System.out.println(s);
        con2.accept("hello4");
    }

    //五、Lambda需要两个以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test5() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(12, 23));
        System.out.println("==============");
        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(com2.compare(20, 32));
    }

    //六、当Lambda体只有一条语句时，return与大括号若有，可以省略
    @Test
    public void test6() {
        Comparator<Integer> com1 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(com1.compare(32, 20));
    }
}
