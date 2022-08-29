package Algorithm.test01;

import org.junit.Test;

public class solution14 {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < index){
                index = strs[i].length();
            }
        }
        boolean flag = true;
        for (int i = 0; i < index; i++) {
            char x = strs[0].charAt(i);
            int n = 1;
            for (int j = 1; j < strs.length; j++) {
                if (x==strs[j].charAt(i)){
                    n++;
                }else {
                    flag = false;
                }
            }
            if (n == strs.length){
                stringBuilder.append(x);
            }
            if (flag == false) break;
        }
        return stringBuilder.toString();
    }
    @Test
    public void test(){
        System.out.println(longestCommonPrefix(new String[]{"cir", "car"}));
    }
}
