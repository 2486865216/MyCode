package Internet;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InterHost {
    @Test
    public void test() throws UnknownHostException {
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        System.out.println(byName);
        System.out.println(byName.getHostName());
        System.out.println(byName.getHostAddress());
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
    }
}
