package com.example.Channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * author ye
 * createDate 2022/3/24  12:48
 */
public class FileChannelDemo01 {
    //FileChannel读取数据到buffer中
    public static void main(String[] args) throws Exception{
        //创建FiLeChannel
        RandomAccessFile file = new RandomAccessFile("Test/src/test.txt","rw");
        FileChannel channel = file.getChannel();

        //创Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //读取数据到buffer中
        int bufferRead = channel.read(byteBuffer);
        while (bufferRead != -1){
            System.out.println("读取了" + byteBuffer);
            byteBuffer.flip();

            while (byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }
            byteBuffer.clear();
            bufferRead = channel.read(byteBuffer);
        }

        file.close();
        System.out.println("结束了");
    }
}
