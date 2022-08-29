package SerializableTest;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = -7455519801769164181L;
    private int no;
    private String name;
    public Student(){

    }

    public Student (int no,String name){
        this.no = no;
        this.name = name;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
