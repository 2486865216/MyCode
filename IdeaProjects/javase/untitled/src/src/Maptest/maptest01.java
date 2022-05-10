package src.Maptest;

import java.util.*;

public class maptest01 {
    public static void main(String[] args) {
        //16*2=1
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"hello");
        map.put(2,"word");
        map.put(3,"first");
        map.put(4,"program");

        System.out.println(map.get(1));
        System.out.println(map.size());
        System.out.println(map.containsKey(1));
        System.out.println(map.containsValue("hello"));

        System.out.println("==============================");

        Set<Integer> keys = map.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()){
            Object key = it.next();
            System.out.println("key:"+key+",value:"+map.get(key));
        }

        System.out.println("=============================");

        for (Integer key:keys){
            System.out.println("key:"+key+",value:"+map.get(key));
        }
        System.out.println("===========================");
        Set<Map.Entry<Integer,String>> entrySet = map.entrySet();
        Iterator<Map.Entry<Integer,String>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry mapset = iterator.next();
            Object key = mapset.getKey();
            Object value = mapset.getValue();
            System.out.println("key:"+key+"="+"value:"+value);
        }

        System.out.println("============================");
        for (Map.Entry mapset:entrySet){
            Object key = mapset.getKey();
            Object value = mapset.getValue();
            System.out.println("2:key:"+key+"="+"value:"+value);
        }
    }
}
