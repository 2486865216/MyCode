package Offer.day10;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/3/27  18:43
 */
public class Solution01 {
    public int translateNum(int num) {
        if (num < 10) return 1;
        char[] chars = String.valueOf(num).toCharArray();
        int[] dp = new int[chars.length];

        dp[0] = 1;
        // 判断到第二格能否以跳两格的形式
        dp[1] = chars[0] - '0' == 1 || (chars[0] - '0' == 2 && chars[1] - '0' < '6') ? 2 : 1;
        for (int i = 2; i < chars.length; i++) {
            int temp = chars[i] - '0';
            dp[i] = temp == 1 || (temp == 2 && chars[i] - '0' < '6') ? dp[i - 1] + dp[i - 2] : dp[i - 1];
        }
        return dp[chars.length - 1];
    }
}
