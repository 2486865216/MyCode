package src.vector;

import java.util.Iterator;
import java.util.Vector;

public class vectortest {
    public static void main(String[] args) {
        Vector vector = new Vector();
        vector.add(100);
        vector.add(100);
        vector.add(100);
        vector.add(100);
        vector.add(100);
        vector.add(100);

        Iterator it = vector.iterator();
        while (it.hasNext()){
            Object obj = it.next();
            System.out.println(obj);
        }
    }
}
