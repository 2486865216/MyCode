package src.Maptest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class maptest02 {
    public static void main(String[] args){
        Map<Integer,String> map = new HashMap<>();
        map.put(100,"hello");
        map.put(200,"word");
        map.put(300,"java");
        map.put(400,"program");

        Set<Map.Entry<Integer,String>> set = map.entrySet();
        for (Map.Entry<Integer,String> entry:set){
            System.out.println(entry.getKey()+"===="+entry.getValue());
        }
    }
}
