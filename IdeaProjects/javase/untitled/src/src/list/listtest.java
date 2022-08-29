package src.list;

import java.util.ArrayList;
import java.util.List;

public class listtest {
    public static void main(String[] args) {
        List mylist = new ArrayList();

        mylist.add(100);
        mylist.add(200);
        mylist.add(300);
        mylist.add(400);

        mylist.add(1,500);
        for (int i = 0; i < mylist.size(); i++) {
            Object obj = mylist.get(i);
            System.out.println(obj);
        }
        mylist.set(1,600);
        for (int i = 0; i < mylist.size(); i++) {
            Object obj = mylist.get(i);
            System.out.println(obj);
        }
        System.out.println(mylist.indexOf(200));
    }
}
