package com.example.AsynchronousFileChannel;

import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * author ye
 * createDate 2022/3/26  13:25
 */
public class Demo01Wried {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("Test/src/test.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("Test".getBytes());

        buffer.flip();

        Future<Integer> write = channel.write(buffer, 0);

        while (!write.isDone());
        buffer.clear();
        System.out.println("wired over");
    }
}
