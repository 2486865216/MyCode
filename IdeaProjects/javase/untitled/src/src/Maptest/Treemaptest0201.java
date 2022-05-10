package src.Maptest;

import java.util.Comparator;
import java.util.TreeSet;

public class Treemaptest0201 {
    public static void main(String[] args) {
        TreeSet<wugui> ts = new TreeSet<>(new wuguicomparator());
//        匿名函数
//        TreeSet<wugui> ts2 = new TreeSet<>(new Comparator<wugui>() {
//            @Override
//            public int compare(wugui o1, wugui o2) {
//                return o1.age-o2.age;
//            }
//        });
        ts.add(new wugui(1000));
        ts.add(new wugui(200));
        ts.add(new wugui(533));
        ts.add(new wugui(5000));
        ts.add(new wugui(356));

        for (wugui w:ts){
            System.out.println(w.age);
        }
    }
}
class wugui{
    int age;
    public wugui(){}

    public wugui(int age){
        this.age = age;
    }
    @Override
    public String toString() {
        return "wugui{" +
                "age=" + age +
                '}';
    }
}
class wuguicomparator implements Comparator<wugui>{
    @Override
    public int compare(wugui o1, wugui o2) {
        return o1.age-o2.age;
    }
}