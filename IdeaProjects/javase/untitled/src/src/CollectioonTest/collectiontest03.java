package src.CollectioonTest;

import java.util.ArrayList;
import java.util.Collection;

public class collectiontest03 {
    public static void main(String[] args) {
        Collection collection = new ArrayList();

        String str1 = new String("abc");
        collection.add(str1);

        String str2 = new String("def");
        collection.add(str2);

        String str3 = new String("abc");

        System.out.println(collection.contains(str3));
    }
}
