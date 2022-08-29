package test01;

import java.util.ResourceBundle;

public class test01 {
    public static void main(String[] args) {
        /*资源绑定器，只能绑定xxx.properties文件。并且这个文件必须在类路径下(src为根目录)
        * 路径后面的扩展名不能写*/
        ResourceBundle bundle = ResourceBundle.getBundle("test");
        String str = bundle.getString("classname");
        System.out.println(str);
    }
}
