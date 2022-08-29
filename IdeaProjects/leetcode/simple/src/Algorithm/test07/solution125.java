package Algorithm.test07;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/3/9  19:34
 */
public class solution125 {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        s = s.trim().toLowerCase().replace(" ", "");
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (left < right){
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(isPalindrome("......a....."));
    }
}
