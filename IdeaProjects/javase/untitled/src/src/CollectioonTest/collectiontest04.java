package src.CollectioonTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class collectiontest04 {
    public static void main(String[] args) {
        Collection collection = new ArrayList();
        collection.add(100);
        collection.add(200);
        collection.add(300);
//      集合的结构发生改变时迭代其必须重新获取
        Iterator it = collection.iterator();

        while (it.hasNext()){
            Object obj = it.next();
            it.remove();
            System.out.println(obj);
        }
    }
}
