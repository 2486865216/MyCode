package com.example.FileLock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * author ye
 * createDate 2022/3/25  13:31
 */
public class FileLockDemo01 {
    public static void main(String[] args) throws Exception {
        String input = "Hello World";
        System.out.println("Input:" + input);

        ByteBuffer buffer = ByteBuffer.wrap(input.getBytes());

        String filePath = "Test/src/test.txt";
        Path path = Paths.get(filePath);

        FileChannel fileChannel = FileChannel.open(
                path, StandardOpenOption.WRITE, StandardOpenOption.APPEND);

        //枷锁
        FileLock lock = fileChannel.lock();
        System.out.println("是否是共享锁:" + lock.isShared());

        fileChannel.write(buffer);
        fileChannel.close();

        //读文件
        readFile(filePath);
    }
    public static void readFile(String filePath) throws Exception {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String tr = bufferedReader.readLine();
        System.out.println("读取出的内容 : ");
        while (tr != null){
            System.out.println(tr);
            tr = bufferedReader.readLine();
        }
        fileReader.close();
        bufferedReader.close();
    }
}
