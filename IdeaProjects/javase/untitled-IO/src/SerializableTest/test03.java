package SerializableTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class test03 {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();

        list.add(new User(100,"hello"));
        list.add(new User(200,"word"));
        list.add(new User(300,"java"));

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user"));

            out.writeObject(list);

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
