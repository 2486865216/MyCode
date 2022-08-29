package src.Maptest;

public class People implements Comparable<People>{
    int age;
    public People(){}
    public People(int age){
        this.age = age;
    }

    @Override
    public int compareTo(People o) {
       return this.age-o.age;
    }

    @Override
    public String toString() {
        return "People{" +
                "age=" + age +
                '}';
    }
}
