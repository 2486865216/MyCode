package com.example.Buffer;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * author ye
 * createDate 2022/3/25  9:29
 */
public class Demo01 {
    @Test
    public void test() throws IOException {
        RandomAccessFile file = new RandomAccessFile("src/test.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //读
        int bytesRead = channel.read(buffer);

        while (bytesRead != -1){
            //转换读模式
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.println((char)buffer.get());
            }
            buffer.clear();
            bytesRead = channel.read(buffer);
        }

        file.close();
    }

    @Test
    public void test1() {
        IntBuffer buffer = IntBuffer.allocate(8);
        for (int i = 0; i < buffer.capacity(); i++) {
            int j = 2 * (i + 1);
            buffer.put(j);
        }

        buffer.flip();

        while (buffer.hasRemaining()){
            int val = buffer.get();
            System.out.println(val);
        }
    }
}
