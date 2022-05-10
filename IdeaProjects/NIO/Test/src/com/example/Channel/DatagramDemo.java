package com.example.Channel;

import com.sun.deploy.net.socket.UnixDomainSocket;
import org.junit.Test;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

/**
 * author ye
 * createDate 2022/3/25  8:49
 */
public class DatagramDemo {
    //发送
    @Test
    public void test () throws IOException, InterruptedException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 10086);

        //发送
        while (true){
            ByteBuffer buffer = ByteBuffer.wrap("发送UDP".getBytes("UTF-8"));
            datagramChannel.send(buffer, socketAddress);
            System.out.println("发送完毕");
            Thread.sleep(1000);
        }
    }
    //接受
    @Test
    public void test1 () throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress(10086);

        //绑定
        datagramChannel.bind(socketAddress);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //接受
        while (true){
            buffer.clear();
            SocketAddress socketAddress1 = datagramChannel.receive(buffer);
            buffer.flip();
            System.out.println(socketAddress1.toString());
            System.out.println(Charset.forName("UTF-8").decode(buffer));
        }
    }

    //测试连接
    @Test
    public void test2() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        //绑定
        datagramChannel.bind(new InetSocketAddress(9999));

        //连接
        datagramChannel.connect(new InetSocketAddress("127.0.0.1", 9999));

        //write
        datagramChannel.write(ByteBuffer.wrap("发送".getBytes("UTF-8")));

        //read
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true){
            buffer.clear();
            datagramChannel.read(buffer);
            buffer.flip();
            System.out.println(Charset.forName("utf-8").decode(buffer));
        }
    }
}
