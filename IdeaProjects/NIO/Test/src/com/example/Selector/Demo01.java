package com.example.Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * author ye
 * createDate 2022/3/25  12:23
 */
public class Demo01 {
    public static void main(String[] args) throws IOException {
        //1、获取Selector选择器
        Selector selector = Selector.open();
        //2、获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3.设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //4、绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //5、将通道注册到选择器上，并制定监听事件为，：“接收”事件约
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //查询已经就绪的通道
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            if (key.isAcceptable()) {

            } else if (key.isConnectable()) {

            } else if (key.isReadable()) {

            } else if (key.isWritable()) {

            }
            iterator.remove();
        }
    }
}
