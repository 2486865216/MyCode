package src.Maptest;

import java.util.TreeSet;

public class Treemaptest {
    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<>();
        ts.add("hello");
        ts.add("word");
        ts.add("first");
        ts.add("program");

        for (String s:ts){
            System.out.println(s);
        }

        TreeSet<Integer> ts2 = new TreeSet<>();
        ts2.add(100);
        ts2.add(50);
        ts2.add(300);
        ts2.add(10);
        ts2.add(900);

        for (Integer s:ts2){
            System.out.println(s);
        }
    }
}
