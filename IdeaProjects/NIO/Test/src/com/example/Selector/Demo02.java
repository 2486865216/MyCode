package com.example.Selector;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * author ye
 * createDate 2022/3/25  12:37
 */
public class Demo02 {
    //客户端代码
    @Test
    public void clientTest() throws IOException {
        //1.获取主机，绑定主机和端口号
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));

        //2.切换到非阻塞模式
        channel.configureBlocking(false);

        //3.创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //4.写入Buffer数据
        buffer.put(new Date().toString().getBytes());

        //5.模式切换
        buffer.flip();

        //6.写入通道
        channel.write(buffer);

        //7,关闭
        channel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        //1.获取服务端通道
        ServerSocketChannel channel = ServerSocketChannel.open();

        //2.切换非阻塞模式
        channel.configureBlocking(false);

        //3.创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //4.绑定端口
        channel.bind(new InetSocketAddress(8080));

        //5.获取selector选择器
        Selector selector = Selector.open();


        //6.通道注册到选择器
        channel.register(selector, SelectionKey.OP_ACCEPT);

        //7.选择器轮询，后续操作
        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    //获取连接
                    SocketChannel accept = channel.accept();

                    //切换非阻塞模式
                    accept.configureBlocking(false);

                    //注册
                    accept.register(selector, SelectionKey.OP_READ);
                } else if (key.isConnectable()) {

                } else if (key.isReadable()) {
                    SocketChannel channel1 = (SocketChannel) key.channel();

                    //读取数据
                    int len = 0;
                    while ((len = channel1.read(buffer)) != -1){
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                } else if (key.isWritable()) {

                }
                iterator.remove();
            }
        }

    }
}
