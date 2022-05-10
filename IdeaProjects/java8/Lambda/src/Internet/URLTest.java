package Internet;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {

    @Test
    public void test() throws MalformedURLException {
        URL url = new URL("https://www.baidu.com/example/test.jpg?id=1&&name=hello");
        System.out.println("协议名:"+url.getProtocol());
        System.out.println("主机名:"+url.getHost());
        System.out.println("端口号:"+url.getPort());
        System.out.println("文件地址:"+url.getPath());
        System.out.println("文件名:"+url.getFile());
        System.out.println("查询名:"+url.getQuery());
    }
}
