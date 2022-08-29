package com.example.Path;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * author ye
 * createDate 2022/3/26  12:18
 */
public class Demo01 {
    public static void main(String[] args) {
        Path path = Paths.get("");

        //代码
        Path projects = Paths.get("d:\\atguigu","projects");
        //代码2
        Path file = Paths.get("d:\\atguigu","projects\\002.txt");

        String originalPath  = "d:\\atguigu\\projects\\..\\yygh-project";
        Path path1 = Paths.get(originalPath);
        System.out.println("path1 = " + path1);
        Path path2 = path1.normalize();
        System.out.println("path2 = " + path2);
    }
}
