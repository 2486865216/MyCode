package IOtest01;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class test08 {
    public static void main(String[] args) {
        try {
            FileWriter file = new FileWriter("untitled-IO/src/IOtest01/text1",true);
            BufferedWriter file2 = new BufferedWriter(file);
            file2.write("hello");
            file2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("kkkk");
    }
}
