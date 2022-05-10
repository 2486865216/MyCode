package Algorithm.test01;

import org.junit.Test;

public class solution5 {
    int index,len;
    public String longestPalindrome(String s){
        if (s.length() < 2){
            return s;
        }
        for (int i = 0; i < s.length()-1; i++) {
            longString(s,i,i);
            longString(s,i,i+1);
        }
        return s.substring(index,index+len);
    }
    public void longString(String s, int left, int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        if (len < right-left-1){
            index = left+1;
            len = right-left-1;
        }
    }
}
