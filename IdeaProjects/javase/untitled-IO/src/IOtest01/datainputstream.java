package IOtest01;

import javafx.scene.effect.Light;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class datainputstream {
    public static void main(String[] args) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("untitled-IO/src/IOtest01/data"));
//            读和写的顺序要一样
            int a = dis.readInt();
            char c = dis.readChar();

            System.out.println(a+"============="+c);

            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
