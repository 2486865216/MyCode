package test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class test04 {
    public static void main(String[] args) throws Exception{
        Class student = Class.forName("test01.Student");

        String classname = student.getName();
        System.out.println("完整类名======>"+classname);

        String simpleName = student.getSimpleName();
        System.out.println("简单类名======>"+simpleName);

//        获取public修饰的属性
        Field[] field = student.getFields();
        System.out.println(field.length);
        System.out.println(field[0].getName());
//        获取所有属性
        Field[] fields = student.getDeclaredFields();
        System.out.println(fields.length);
        for (Field field1:fields){
//            获取属性的修饰符
            int i = field1.getModifiers();
            String modString = Modifier.toString(i);
            System.out.println(modString);
//            获取属性的类型
            Class fieldType = field1.getType();
//            获取属性的名字
            System.out.println("fieldName  "+field1.getName());
            System.out.println("fieldType  "+fieldType);
            System.out.println("---------------------");
        }
    }
}
