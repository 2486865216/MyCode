package com.example.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * author ye
 * createDate 2022/3/26  14:37
 */
public class ClientThread implements Runnable{
    private Selector selector;

    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        for(;;){
            int count = 0;
            try {
                count = selector.select();
                if (count == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    if (next.isReadable()) {
                        readOperation(selector, next);
                    }
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readOperation(Selector selector, SelectionKey key) throws IOException {
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
        //4将channel再次注册到选择器上，监听可读
        channel.register(selector, SelectionKey.OP_READ);

        if (message.length() > 0){
            System.out.println(message);
        }
    }
}
