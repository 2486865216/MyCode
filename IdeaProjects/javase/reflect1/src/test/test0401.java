package test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class test0401 {
    public static void main(String[] args) throws Exception {
        Class student = Class.forName("test01.Student");
        StringBuilder s = new StringBuilder();

        s.append(Modifier.toString(student.getModifiers()) +" class "+student.getSimpleName()+"{");
        Field[] fields = student.getDeclaredFields();
        for (Field field:fields){
            s.append("\n");
            s.append("\t");
            s.append(Modifier.toString(field.getModifiers()));
            if (field.getModifiers()!=0){
                s.append(" ");
            }
            s.append(field.getType().getSimpleName());
            s.append(" ");
            s.append(field.getName());
            s.append(";\n");
        }

        s.append("}");
        System.out.println(s);
    }
}
