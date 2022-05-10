package Stream;

import java2.Employee;
import java2.EmployeeDate;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 终止操作
 * author ye
 * createDate 2022/1/24  11:20
 */
public class Test03 {
    @Test
    public void test() {
        List<Employee> list = EmployeeDate.getEmployees();
        //allMath(Predicate p)-检查是否匹配所有的元素
        boolean b = list.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(b);
        //anyMath(Predicate p)-检查是否至少匹配一个元素
        boolean b1 = list.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(b1);
        //noneMath(Predicate p)-检查是否没有匹配的元素 有：false，没有：true
        boolean none = list.stream().noneMatch(e -> e.getName().startsWith("张"));
        System.out.println(none);
        //findFirst-返回第一个元素
        Optional<Employee> first = list.stream().findFirst();
        System.out.println(first);
        //findAny-返回任意一个元素
        Optional<Employee> find = list.parallelStream().findAny();
        System.out.println(find);
    }

    @Test
    public void test2() {
        List<Employee> list = EmployeeDate.getEmployees();
        //count-返回个数
        long count = list.stream().count();
        System.out.println(count);
        //max(Comparator c)-返回最大值
        Stream<Double> doubleStream = list.stream().map(e -> e.getSalary());
        Optional<Double> max = doubleStream.max(Double::compareTo);
        System.out.println(max);
        //min(Comparator c)-返回最小值
        Optional<Employee> min = list.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min);
    }
    //归约
    @Test
    public void test3() {
        //reduce(T identify, BinaryOperator)-将流中的元素反复结合起来，得到一个值，返回T
        //1-10的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8 ,9 ,10);
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        //reduce(BinaryOperator)-将流中的元素反复结合起来，得到一个值，返回Optional<T>
        List<Employee> list1 = EmployeeDate.getEmployees();
        Stream<Double> doubleStream = list1.stream().map(Employee::getSalary);
        Optional<Double> reduce1 = doubleStream.reduce(Double::sum);
        System.out.println(reduce1);
    }
    //收集
    @Test
    public void test4() {
        List<Employee> list = EmployeeDate.getEmployees();
        //collect(Collector c)-将流转换为其它形式
        List<Employee> collect = list.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
