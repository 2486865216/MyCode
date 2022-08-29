package com.example.AsynchronousFileChannel;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.concurrent.Future;

/**
 * author ye
 * createDate 2022/3/26  12:46
 */
public class Demo01 {
    public static void main(String[] args) throws Exception {
        //(1)创建了一个AsynchronousFileChannel,
        Path path = Paths.get("Test/src/test.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        //(2)创建一个ByteBuffer,它被传递给read()方法作为参数，以及一个0的位置。
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> read = channel.read(buffer, 0);

        //(3)在调用read()之后，循环，直到返回的isDone()方法返回true。
        while (!read.isDone());

        //(4)读取操作完成后，数据读取到ByteBuffer中，然后打印到System.out中。
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
        channel.close();
    }
}
