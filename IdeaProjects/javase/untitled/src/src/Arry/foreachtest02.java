package src.Arry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class foreachtest02 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("word");

        Iterator it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

        for(String s: list){
            System.out.println(s);
        }
    }
}
