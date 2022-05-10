package Offer.Another;

import java.util.*;

/**
 * author ye
 * createDate 2022/5/1  19:31
 */
public class solution20 {
    public boolean isValid(String s) {
        int n = s.length();
        if ((n & 1) == 1) return false;
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<Character, Character>(){{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                if (stack.isEmpty() || stack.peek() != map.get(c)){
                    return false;
                }
                stack.pop();
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
