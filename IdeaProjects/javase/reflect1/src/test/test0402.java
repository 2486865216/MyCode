package test;

import java.lang.reflect.Field;

public class test0402 {
    public static void main(String[] args) throws Exception {
        Class student = Class.forName("test01.Student");
        Object obj = student.newInstance();
        Field noField = student.getDeclaredField("no");
        noField.set(obj,1111);
        System.out.println(noField.get(obj));

        Field nameField = student.getDeclaredField("name");
//        打破封装
        nameField.setAccessible(true);
        nameField.set(obj,"123");
        System.out.println(nameField.get(obj));
    }
}
