package SerializableTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class test02 {
    public static void main(String[] args) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("student"));
            Object b = in.readObject();
            System.out.println(b.toString());

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
