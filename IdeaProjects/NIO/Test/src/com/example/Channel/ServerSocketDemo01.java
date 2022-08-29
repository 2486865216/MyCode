package com.example.Channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * author ye
 * createDate 2022/3/24  14:02
 */
public class ServerSocketDemo01 {
    public static void main(String[] args) throws Exception{
        //端口号
        int port  = 8000;

        ByteBuffer buffer = ByteBuffer.wrap("Hello World".getBytes());

        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.socket().bind(new InetSocketAddress(port));

        //设置非阻塞模式
        ssc.configureBlocking(false);

        //监听新连接传入
        while (true){
            System.out.println("Waiting for connections");
            SocketChannel sc = ssc.accept();
            if (sc == null){
                System.out.println("null");
                Thread.sleep(2000);
            }else {
                System.out.println("Incoming connection from:" + sc.socket().getRemoteSocketAddress());
                buffer.remaining(); //指针0
                sc.write(buffer);
                sc.close();
            }
        }
    }
}
