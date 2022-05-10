package java2;

import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * author ye
 * createDate 2022/1/24
 */
public class MethodRefTest02 {
    /**
     * 构造器引用
     * Supplier中的T get()
     */
    @Test
    public void test() {
        Supplier<Employee> sup = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        Supplier<Employee> sup1 = () -> new Employee();
        System.out.println(sup1.get());
        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());
    }

    /**
     * Function中的R apply(T t)
     */
    @Test
    public void test2() {
        Function<Integer, Employee> function = id -> new Employee(id);
        System.out.println(function.apply(1001));
        Function<Integer, Employee> function2 = Employee::new;
        System.out.println(function2.apply(1002));
    }
    /**
     * BiFunction中的R apply(T t, U u)
     */
    @Test
    public void test3() {
        BiFunction<Integer, String, Employee> biFunction1 = (id, name) -> new Employee(id,name);
        System.out.println(biFunction1.apply(1001, "hello"));
        BiFunction<Integer, String, Employee> biFunction2 = Employee::new;
        System.out.println(biFunction2.apply(1002, "word"));
    }
    /**
     * 数组引用
     * Function中的R apply(T t)
     */
    @Test
    public void test4() {
        Function<Integer, String[]> func1 = length -> new String[length];
        String[] arr1 = func1.apply(5);
        System.out.println(Arrays.toString(arr1));
        Function<Integer, String[]> func2 = String[]::new;
        String[] arr2 = func2.apply(10);
        System.out.println(Arrays.toString(arr2));
    }
}
