package src.CollectioonTest;

import java.util.ArrayList;
import java.util.Collection;

public class Colletiontest {
    public static void main(String[] args) {
        Collection collection = new ArrayList();
        collection.add(100);
        System.out.println("size = "+collection.size());

        collection.clear();
        System.out.println("size = "+collection.size());

        collection.add(100);
        collection.add(222);
        collection.add(333);
        collection.add(555);

        collection.remove(100);
        System.out.println("size = "+collection.size());

        System.out.println(collection.isEmpty());

        Object[] a = collection.toArray();
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
