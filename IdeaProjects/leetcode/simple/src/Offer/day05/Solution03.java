package Offer.day05;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/22  19:39
 */
public class Solution03 {
    public char firstUniqChar(String s) {
        //for (int i = 0; i < s.length(); i++) {
        //    char ch = s.charAt(i);
        //    if (s.indexOf(ch) == i && s.indexOf(ch, i + 1) == -1){
        //        return ch;
        //    }
        //}
        //return ' ';
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (Character character : map.keySet()) {
            if (map.get(character) == 1){
                return character;
            }
        }
        return ' ';
    }
}
