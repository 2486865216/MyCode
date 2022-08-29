package com.example.Channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * author ye
 * createDate 2022/3/25  8:33
 */
public class SocketChannelDemo {
    public static void main(String[] args) throws IOException {
        //创建SocketChannel
        //SocketChannel socketChannel = SocketChannel
        //        .open(new InetSocketAddress("www.baidu.com", 80));

        //SocketChannel socketChannel1 = SocketChannel.open();
        //socketChannel1.connect(new InetSocketAddress("www.baidu.com", 80));

        /*socketChannel.isOpen();//测试SocketChannel是否为open状东
        socketChannel.isConnected();//测试SocketChannel是否已经被连接
        socketChannel.isConnectionPending();//测试SocketChannel是否正在进行连接
        socketChannel.finishConnect();//校验正在进行套接字连接的SocketChannel是否已经完成连接*/

        SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("www.baidu.com", 80));
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("read over");

        socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE,
                        Boolean.TRUE)
                .setOption(StandardSocketOptions.TCP_NODELAY, Boolean.TRUE);

        socketChannel.getOption(StandardSocketOptions.SO_KEEPALIVE);
        socketChannel.getOption(StandardSocketOptions.SO_RCVBUF);
    }
}
