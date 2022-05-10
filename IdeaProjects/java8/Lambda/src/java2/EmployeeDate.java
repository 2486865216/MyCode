package java2;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDate {
    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1001, "张三", 30, 6000.36));
        list.add(new Employee(1002, "张三", 25, 1000.36));
        list.add(new Employee(1003, "张三", 24, 2000.36));
        list.add(new Employee(1004, "张三", 35, 5000.36));
        list.add(new Employee(1005, "张三", 75, 7000.36));
        list.add(new Employee(1006, "张三", 24, 4000.36));
        list.add(new Employee(1007, "张三", 24, 5000.36));

        return list;
    }
}
