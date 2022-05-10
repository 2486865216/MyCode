package src.CollectioonTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Collectiontest05 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
//        非线程安全转线程安全
        Collections.synchronizedList(list);

        list.add("abc");
        list.add("abd");
        list.add("bcd");
        list.add("aab");


//        排序

        Collections.sort(list);
        for (String s:list){
            System.out.println(s);
        }

    }
}
