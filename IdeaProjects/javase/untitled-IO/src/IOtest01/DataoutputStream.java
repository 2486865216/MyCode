package IOtest01;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataoutputStream {
    public static void main(String[] args) {
        FileOutputStream file = null;
        try {
            file = new FileOutputStream("untitled-IO/src/IOtest01/data");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DataOutputStream dos = new DataOutputStream(file);

        int a = 100;
        char c = 'a';
        try {
            dos.writeInt(a);
            dos.writeChar(c);

            dos.flush();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
