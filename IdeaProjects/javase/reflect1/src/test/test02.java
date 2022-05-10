package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class test02 {
    public static void main(String[] args) throws Exception{
        FileReader file = new  FileReader("reflect.properties");
        Properties pro = new Properties();

        pro.load(file);
        file.close();

        String s = pro.getProperty("classname");
        System.out.println(s);

        Class c = Class.forName(s);
//        newInstance()调用的是无参构造方法
        Object obj = c.newInstance();
        System.out.println(obj);
    }
}
