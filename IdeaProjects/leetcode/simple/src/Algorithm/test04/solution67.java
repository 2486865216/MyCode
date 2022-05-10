package Algorithm.test04;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/1/26  19:48
 */
public class solution67 {
    public String addBinary(String a, String b) {
        int len = Math.max(a.length(), b.length());
        int alen = len -a.length();
        int blen = len -b.length();
        int carry = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            if (i >= len - a.length()){
                carry = carry + a.charAt(i - alen) - '0';
            }
            if (i >= len - b.length()){
                carry = carry + b.charAt(i - blen) - '0';
            }
            stringBuilder.append((char) (carry % 2 + '0'));
            carry = carry / 2;
        }
        if (carry > 0) {
            stringBuilder.append('1');
        }
        return stringBuilder.reverse().toString();
    }

    @Test
    public void test() {
        String s = addBinary("1010", "1011");
        System.out.println(s);
    }
}
