package IOtest01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class printstream {
    public static void main(String[] args) {
//        连起来写
        System.out.println("hello");


        PrintStream ps = System.out;
        ps.println("hello word!");
        ps.println("hello java!");

        try {
            PrintStream ps1 = new PrintStream(new FileOutputStream("untitled-IO/src/IOtest01/text3",true));
            System.setOut(ps1);
            System.out.println("hello fffff");
            System.setOut(ps);
            System.out.println("hello");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
