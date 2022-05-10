package Algorithm.test03;

import org.junit.Test;

import java.util.*;

public class solution49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] strArray = str.toCharArray();
            Arrays.sort(strArray);
            String string = new String(strArray);
            List<String> list = map.getOrDefault(string, new ArrayList<>());
            list.add(str);
            map.put(string, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    @Test
    public void test() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("hello");
        map.put("name",list);
        List<String> list1 = map.getOrDefault("age",new ArrayList<>());
        for (String s : list1) {
            System.out.println(s);
        }
    }
}
