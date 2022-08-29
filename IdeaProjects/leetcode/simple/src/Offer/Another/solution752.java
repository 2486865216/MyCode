package Offer.Another;

import java.util.*;

/**
 * author ye
 * createDate 2022/4/30  20:07
 */
public class solution752 {
    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) return 0;

        Set<String> setDeadens = new HashSet<>(Arrays.asList(deadends));
        if (setDeadens.contains("0000")) return -1;

        int step = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer("0000");
        set.add("0000");

        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (String s : getAround(poll)) {
                    if (!set.contains(s) && !setDeadens.contains(s)){
                        if (s.equals(target)){
                            return step;
                        }
                        queue.offer(s);
                        set.add(s);
                    }
                }

            }
        }
        return -1;
    }
    private char numPre(char x){
        return x == '0' ? '9' : (char) (x - 1);
    }
    private char numNext(char x){
        return x == '9' ? '0' : (char) (x + 1);
    }
    private List<String> getAround(String str){
        List<String> list = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < 4; i++) {
            char num = chars[i];
            chars[i] = numPre(num);
            list.add(new String(chars));
            chars[i] = numNext(num);
            list.add(new String(chars));
            chars[i] = num;
        }
        return list;
    }
}
