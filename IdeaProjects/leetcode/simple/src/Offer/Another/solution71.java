package Offer.Another;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * author ye
 * createDate 2022/5/1  19:48
 */
public class solution71 {
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String name : names) {
            if ("..".equals(name)){
                if (!stack.isEmpty()){
                    stack.pollLast();
                }
            }else if ( name.length() > 0 && !".".equals(name)){
                stack.offerLast(name);
            }
        }
        StringBuilder ans = new StringBuilder();
        if (stack.isEmpty()){
            return ans.append("/").toString();
        }else {
            while (!stack.isEmpty()){
                ans.append("/").append(stack.pollFirst());
            }
        }
        return ans.toString();
    }

    @Test
    public void test() {
        simplifyPath("/home/");
    }
}
