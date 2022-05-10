package com.example.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * author ye
 * createDate 2022/3/26  14:05
 */
public class ChatServer {
    //服务器端启动
    public void startServer() throws IOException {
        //1.创建selector
        Selector selector = Selector.open();

        //2.创建ServerSocketChannel
        ServerSocketChannel channel = ServerSocketChannel.open();

        //3.绑定监听端口
        channel.bind(new InetSocketAddress(8080));
        channel.configureBlocking(false);

        //4循环，等待有新链接接入
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");

        //5循环，等待有新链接接入
        //while (true)
        for(;;){
            int count = selector.select();

            if (count == 0){
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                //6根据就绪状态，凋用对应方法实现具体业务操作
                //6.1如果accept状态
                if (next.isAcceptable()){
                    acceptOperation(channel, selector);
                }
                //6.2如果可读状态
                else if (next.isReadable()){
                    readOperation(selector, next);
                }
                iterator.remove();
            }
        }
    }

    //处理可读状态操作
    private void readOperation(Selector selector, SelectionKey key) throws IOException {
        //1从SelectionKey.获取到已经就绪的通道
        SocketChannel channel = (SocketChannel) key.channel();
        //2创buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //3循环读取客户瑞消息
        int read = channel.read(buffer);
        String message = "";
        if (read > 0){
            buffer.flip();

            message += Charset.forName("UTF-8").decode(buffer);

        }
        //4将channel再次注册到选择器上，监听可读状
        channel.register(selector, SelectionKey.OP_READ);

        //把客户端发送消息，广播到其他客户端
        if (message.length() > 0){
            System.out.println(message);
            CastOtherClient(message, selector, channel);
        }
    }

    //广播到其它客户端
    private void CastOtherClient(String message, Selector selector, SocketChannel channel) throws IOException {
        //1获取所有已经接入channel
        Set<SelectionKey> keys = selector.keys();

        //2循环想所有channel广播消息
        for (SelectionKey key : keys) {
            Channel channel1 = key.channel();
            if (channel1 instanceof SocketChannel && channel1 != channel){
                ((SocketChannel) channel1).write(Charset.forName("UTF-8").encode(message));
            }
        }
    }

    //处理接入状态操作
    private void acceptOperation(ServerSocketChannel channel, Selector selector) throws IOException {
        //1接入状态，创建socketChannel
        SocketChannel accept = channel.accept();

        //2把socketChannel设置非阻塞模式
        accept.configureBlocking(false);

        //3把channel注册到seLector,选择器上，监听可读状态
        accept.register(selector,SelectionKey.OP_READ);

        //4客户端回复信息
        accept.write(Charset.forName("UTF-8").encode("欢迎进入聊天室"));
    }

    public static void main(String[] args) {
        try {
            new ChatServer().startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
