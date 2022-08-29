package com.example.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * author ye
 * createDate 2022/3/26  12:26
 */
public class Demo01 {
    public static void main(String[] args) {
        /*Path path = Paths.get("Test/src/fileDemo01");
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*Path path = Paths.get("Test/src/test.txt");
        Path path1 = Paths.get("Test/src/fileDemo01/test.txt");
        try {
            Files.copy(path, path1, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*Path path = Paths.get("Test/src/test02.txt");
        Path path1 = Paths.get("Test/src/fileDemo01/test.txt");
        try {
            Files.move(path, path1, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Path path = Paths.get("Test/src/test.txt");
        Path path1 = Paths.get("Test/src/fileDemo01/test.txt");
        try {
            Files.delete(path1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
