package Offer.Another;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/5/10  8:06
 */
public class solution2571 {
    List<String> list = new ArrayList();
    public String[] permutation(String S) {
        char[] chars = S.toCharArray();
        boolean[] visited = new boolean[chars.length];
        dfs(chars, new StringBuilder(), visited);
        return list.toArray(new String[list.size()]);
    }

    private void dfs(char[] chars, StringBuilder s, boolean[] visited) {
        if (s.length() == chars.length){
            list.add(s.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (visited[i]){
                continue;
            }
            s.append(chars[i]);
            visited[i] = true;
            dfs(chars, s, visited);
            visited[i] = false;
            s.deleteCharAt(s.length() - 1);
        }
    }
}
