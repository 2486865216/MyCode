package Algorithm.test04;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * author ye
 * createDate 2022/2/10  20:24
 */
public class solution71 {
    public String simplifyPath(String path){
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String name : names) {
            if ("..".equals(name)){
                if (!stack.isEmpty()){
                    stack.pollLast();
                }
            }else if (name.length() > 0 && !".".equals(name)){
                stack.offerLast(name);
            }
        }
        StringBuilder ans = new StringBuilder();
        if (stack.isEmpty()){
            ans.append("/");
        }else {
            while (!stack.isEmpty()){
                ans.append("/");
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }
}
