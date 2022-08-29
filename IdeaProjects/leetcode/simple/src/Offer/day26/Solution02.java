package Offer.day26;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/12  19:11
 */
public class Solution02 {
    public int strToInt(String str) {
        int len = str.length();
        int i = 0;
        if (len == 0) return 0;
        while (str.charAt(i) == ' '){
            if (++i == len) return 0;
        }
        int sum = 0, sign = 1, bnary = Integer.MAX_VALUE / 10;
        if (str.charAt(i) == '-'){
            sign = -1;
        }
        if (str.charAt(i) == '-' || str.charAt(i) == '+') i++;
        for (int j = i; j < len; j++) {
            if (str.charAt(j) < '0' || str.charAt(j) > '9') break;
            if (sum > bnary || sum == bnary && str.charAt(j) > '7'){
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            sum = sum * 10 + (str.charAt(j) - '0');
        }
        return sign * sum;
    }
}
