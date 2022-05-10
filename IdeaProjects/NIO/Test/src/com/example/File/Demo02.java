package com.example.File;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * author ye
 * createDate 2022/3/26  12:37
 */
public class Demo02 {
    public static void main(String[] args) {
        Path path = Paths.get("Test/src");
        String s = File.separator + "001.txt";
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileString = file.toAbsolutePath().toString();

                    if (fileString.endsWith(s)){
                        System.out.println("file found in path " + file.toAbsolutePath());
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
