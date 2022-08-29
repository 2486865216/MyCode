package src.Maptest;

import java.util.TreeSet;

public class Treemaptest02 {
    public static void main(String[] args) {
        TreeSet<People> ts = new TreeSet<>();

        People p1 = new People(100);
        People p2 = new People(58);
        People p3 = new People(25);
        People p4 = new People(42);
        People p5 = new People(25);
        ts.add(p1);
        ts.add(p2);
        ts.add(p3);
        ts.add(p4);
        ts.add(p5);

        for (People p:ts){
            System.out.println(p);
        }
    }
}
