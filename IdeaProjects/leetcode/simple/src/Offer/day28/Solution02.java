package Offer.day28;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author ye
 * createDate 2022/4/14  21:46
 */
public class Solution02 {
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Set<String> list = new HashSet<>();
        boolean[] visited = new boolean[s.length()];
        dfs(chars, "", visited, list);
        return list.toArray(new String[0]);
    }

    private void dfs(char[] chars, String s, boolean[] visited, Set<String> list) {
        if (chars.length == s.length()){
            list.add(s);
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(chars, s + String.valueOf(chars[i]), visited, list);
            visited[i] = false;
        }
    }
}
