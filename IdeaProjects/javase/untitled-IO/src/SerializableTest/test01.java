package SerializableTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class test01 {
    public static void main(String[] args) {
        Student student = new Student(1000,"list");

//        序列化

        try {
            ObjectOutputStream stu = new ObjectOutputStream(new FileOutputStream("student",true));

            stu.writeObject(student);

            stu.flush();
            stu.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class hhh implements Serializable{
    private static final long serialVersionUID = -7652235009260155089L;
}
