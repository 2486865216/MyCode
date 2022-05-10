package Stream;

import java2.Employee;
import java2.EmployeeDate;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1.Stream关注的是对数据的运算，与CPU打交道
 *  集合关注的是数据的存储，与内存打交道
 * 2.
 *  Stream自己不会存储元素。
 *  Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 *  Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
 * 3.Stream 执行流程
 *  Stream的实例化
 *  一系列的中间操作（过滤、映射、...）
 *  终止操作
 * 4.说明：
 *  4.1一个中间操作链，对数据源的数据进行处理
 *  4.2一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 * author ye
 * createDate 2022/1/24  10:24
 */
public class Test01 {
    //创建Stream方法一:通过集合
    @Test
    public void test() {
        List<Employee> employees = EmployeeDate.getEmployees();
        //顺序流
        Stream<Employee> stream = employees.stream();
        //并行流
        Stream<Employee> stream1 = employees.parallelStream();
    }
    //通过数组
    @Test
    public void test1() {
        int[] arr = new int[]{1,2,3,4,5};
        IntStream intStream = Arrays.stream(arr);
        Employee employee = new Employee(1001, "hello");
        Employee employee1 = new Employee(1002, "word");
        Employee[] employeeArray = new Employee[]{employee, employee1};
        Stream<Employee> stream = Arrays.stream(employeeArray);
    }
    //通过Stream的of()
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
    }
    //创建无限流
    @Test
    public void test4() {
        //迭代
        Stream.iterate(0, t -> t+2).limit(10).forEach(System.out::println);
        //生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
