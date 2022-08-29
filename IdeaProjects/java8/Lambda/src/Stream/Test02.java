package Stream;

import java2.Employee;
import java2.EmployeeDate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * author ye
 * createDate 2022/1/24  10:36
 */
public class Test02 {
    @Test
    public void test() {
        List<Employee> list = EmployeeDate.getEmployees();
        Stream<Employee> stream = list.stream();
        //过滤
        stream.filter(e -> e.getSalary() > 5000).forEach(System.out::println);
        System.out.println();
        //截断
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();
        //跳过,超出范围返回空流
        list.stream().skip(3).forEach(System.out::println);
        System.out.println();
        //筛选,通过元素的hashcode和equals去掉重复元素
        list.add(new Employee(1008, "hello", 18, 6000.1));
        list.add(new Employee(1008, "hello", 18, 6000.1));
        list.add(new Employee(1008, "hello", 18, 6000.1));
        list.add(new Employee(1008, "hello", 18, 6000.1));
        list.add(new Employee(1008, "hello", 18, 6000.1));
        list.add(new Employee(1008, "hello", 18, 6000.1));

        list.stream().distinct().forEach(System.out::println);
    }
    //映射
    @Test
    public void test2() {
        //map(Function f)-接收一个函数作为参数，将元素转换成其它形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素
        List<String> list = Arrays.asList("aa","bb","cc","dd","ee");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        List<Employee> list1 = EmployeeDate.getEmployees();
        Stream<String> nameStream = list1.stream().map(Employee::getName);
        nameStream.filter(name -> name.length()>=2).forEach(System.out::println);
        System.out.println();

        Stream<Stream<Character>> streamStream = list.stream().map(Test02::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });

        //flatmap(Function f)-接收一个函数作为参数，将流中的每个值都转换为另一个流，然后把所有流连接成一个流。
        list.stream().flatMap(Test02::fromStringToStream).forEach(System.out::println);
    }
    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    //排序
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(3,5,1,3,4,6,8,5,25,6,7,5);
        //自然排序
        list.stream().sorted().forEach(System.out::println);
        //定制排序
        list.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }).forEach(System.out::println);
    }
}
