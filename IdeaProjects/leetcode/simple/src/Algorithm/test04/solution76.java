package Algorithm.test04;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/2/13  19:36
 */
public class solution76 {
    Map<Character, Integer> sCount = new HashMap<>();
    Map<Character, Integer> tCount = new HashMap<>();
    int left = -1;
    int right = -1;
    int len = Integer.MAX_VALUE;
    public String minWindow(String s, String t) {
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);
        }
        int leftSmall  = 0;
        int rightSmall  = -1;
        while (rightSmall < s.length()){
            rightSmall++;
            if (rightSmall < s.length() && tCount.containsKey(s.charAt(rightSmall))){
                sCount.put(s.charAt(rightSmall), sCount.getOrDefault(s.charAt(rightSmall), 0) + 1);
            }
            while (check() && leftSmall <= rightSmall){
                if (rightSmall - leftSmall + 1 < len){
                    left = leftSmall;
                    right = rightSmall;
                    len = rightSmall - leftSmall + 1;
                }
                if (tCount.containsKey(s.charAt(leftSmall))){
                    sCount.put(s.charAt(leftSmall),sCount.getOrDefault(s.charAt(leftSmall), 0) - 1);
                }
                leftSmall++;
            }
        }
        return right == -1 ? "" : s.substring(left,right + 1);
    }
    boolean check(){
        for (Map.Entry<Character, Integer> entry : tCount.entrySet()) {
            int val = (int) entry.getValue();
            if (sCount.getOrDefault(entry.getKey(), 0) < val){
                return false;
            }
        }
        return true;
    }
}
