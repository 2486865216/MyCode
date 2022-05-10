package com.example.Channel;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * author ye
 * createDate 2022/3/24  13:29
 */
public class FileChannelDemo02 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("Test/src/test02.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        String value = "Hello World";

        buffer.clear();
        buffer.put(value.getBytes());

        buffer.flip();

        while (buffer.hasRemaining()){
            channel.write(buffer);
        }

        channel.close();

    }
}
