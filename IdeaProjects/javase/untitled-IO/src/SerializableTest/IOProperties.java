package SerializableTest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class IOProperties {
    public static void main(String[] args) throws IOException {
/*
        * 非常好的一个设计理念：
        以后经常改变的数据，可以单独写到一个文件中，使用程序动态读取。将来只需要修改这个文件的内容，
        * java代码不需要改动，不需要重新编译，服务器也不需要重启。就可以拿到动态的信息。
        类似于以上机制的这种文件被称为配置文件。
        并且当配置文件中的内容格式是：
            key1=value
            key2=value
        的时候，我们把这种配置文件叫做属性配置文件。
        java规范中有要求：属性配置文件建议以.properties结尾，但这不是必须的。
        * 这种以.properties结尾的文件在java中被称为：属性配置文件。
        其中Properties对输是专门存放属性配置文件内容的一个类。
*/
//    Properties是一个Map集合
        Properties pro = new Properties();
        FileReader file = new FileReader("properties.properties");

        pro.load(file);
        file.close();

        System.out.println(pro.getProperty("username"));
        System.out.println(pro.getProperty("mima"));
    }
}
