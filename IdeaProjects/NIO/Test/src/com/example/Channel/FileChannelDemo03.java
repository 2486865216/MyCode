package com.example.Channel;

import sun.rmi.runtime.Log;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * author ye
 * createDate 2022/3/24  13:41
 */
public class FileChannelDemo03 {
    public static void main(String[] args) throws  Exception{

        RandomAccessFile file1 = new RandomAccessFile("Test/src/test.txt","rw");
        FileChannel fromChannel = file1.getChannel();

        RandomAccessFile file2 = new RandomAccessFile("Test/src/test02.txt","rw");
        FileChannel toChannel = file2.getChannel();

        //fromChannel传输到toChannel
        int position = 0;
        long size = toChannel.size();
        toChannel.transferTo(position, size, fromChannel);

        file1.close();
        file2.close();
    }
}
