package com.example.Buffer;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * author ye
 * createDate 2022/3/25  10:01
 */
public class BufferDemo02 {
    //缓冲区分片
    @Test
    public void test() {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        //创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer buffer1 = buffer.slice();

        //改变子缓冲区内容
        for (int i = 0; i < buffer1.capacity(); i++) {
            buffer1.put(i, (byte) (buffer1.get(i) * 2));
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }

    //只读缓冲区
    @Test
    public void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer buffer1 = buffer.asReadOnlyBuffer();

        buffer.put(3, (byte) 99);

        buffer1.flip();
        while (buffer1.hasRemaining()){
            System.out.println(buffer1.get());
        }
    }

    //直接缓冲区
    @Test
    public void test2() throws IOException {
        RandomAccessFile file = new RandomAccessFile("src/test.txt", "rw");
        RandomAccessFile file3 = new RandomAccessFile("src/test03.txt", "rw");

        FileChannel channel = file.getChannel();
        FileChannel channel3 = file3.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true){
            buffer.clear();
            int r = channel.read(buffer);
            if (r == -1){
                break;
            }
            buffer.flip();
            channel3.write(buffer);
        }
        file.close();
        file3.close();
    }

    //内存映射文件I/O
    private static final int start = 0;
    private static final int size = 1024;
    @Test
    public void test3() throws IOException {
        RandomAccessFile file = new RandomAccessFile("src/test.txt", "rw");
        FileChannel channel = file.getChannel();

        MappedByteBuffer mapped = channel.map(FileChannel.MapMode.READ_WRITE, start, size);

        mapped.put(0, (byte) 97);
        mapped.put(1023, (byte) 999);

        while (mapped.hasRemaining()){
            System.out.println((char)mapped.get());
        }

        file.close();
    }
}
