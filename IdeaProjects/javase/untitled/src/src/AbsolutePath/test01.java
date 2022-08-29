package src.AbsolutePath;

import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

public class test01 {
    public static void main(String[] args) throws Exception{
        String path = null;
        path = Thread
                .currentThread()
                .getContextClassLoader()
                .getResource("test.properties").getPath();
//        文件更目录在src下（类路径下）
        System.out.println(path);
//        以流的形式返回
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties");
        Properties test = new Properties();
        test.load(in);
        System.out.println(test.getProperty("classname"));
    }
}
