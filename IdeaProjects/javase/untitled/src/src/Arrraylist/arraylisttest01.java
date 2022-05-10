package src.Arrraylist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class arraylisttest01 {
    public static void main(String[] args) {
        List list = new ArrayList();
        List list2 = new ArrayList(20);

        Collection c = new HashSet();
        c.add(100);
        c.add(200);

        List list1 = new ArrayList(c);
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
    }
}
