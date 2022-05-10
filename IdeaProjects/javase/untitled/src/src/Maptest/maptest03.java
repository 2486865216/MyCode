package src.Maptest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class maptest03 {
    public static void main(String[] args) {
        Set<Student> set = new HashSet<>();
        Student student1 = new Student("zhangsan");
        Student student2 = new Student("zhangsan");

        System.out.println(student1.hashCode());
        System.out.println(student2.hashCode());

        set.add(student1);
        set.add(student2);
        System.out.println(student1.equals(student2));
        System.out.println(set.size());
    }
}
