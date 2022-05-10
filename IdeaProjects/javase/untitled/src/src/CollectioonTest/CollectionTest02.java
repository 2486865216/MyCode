package src.CollectioonTest;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CollectionTest02 {
    public static void main(String[] args) {
        Collection c = new HashSet();
        c.add(100);
        c.add(200);
        c.add(300);

        Iterator it = c.iterator();
        while (it.hasNext()){
            Object obj = it.next();
            System.out.println(obj);
        }
    }
}
