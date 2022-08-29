package com.example.Pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * author ye
 * createDate 2022/3/25  13:13
 */
public class Demo01 {
    public static void main(String[] args) throws IOException {
        //1. 获取管道
        Pipe pipe = Pipe.open();

        //2. 获取sink通道
        Pipe.SinkChannel sink = pipe.sink();

        //3.创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Hello World".getBytes());
        buffer.flip();

        //4.写入数据
        sink.write(buffer);

        //5。获取source通道
        Pipe.SourceChannel source = pipe.source();

        //6.读取数据
        buffer.flip();

        int len = source.read(buffer);

        System.out.println(new String(buffer.array(), 0, len));

        //7.关闭
        source.close();
        sink.close();
    }
}
