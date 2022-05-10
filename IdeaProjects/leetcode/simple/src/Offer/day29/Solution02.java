package Offer.day29;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/15  18:58
 */
public class Solution02 {
    public int nthUglyNumber(int n) {
        if (n <= 0) return -1;
        int[] dp = new int[n];
        dp[0] = 1;
        int id1 = 0, id2 = 0, id3 = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[id1] * 2, Math.min(dp[id2] * 3, dp[id3] * 5));
            if (dp[i] == dp[id1] * 2){
                id1++;
            }
            if (dp[i] == dp[id2] * 3){
                id2++;
            }
            if (dp[i] == dp[id3] * 5){
                id3++;
            }
        }
        return dp[n - 1];
    }

    @Test
    public void test() {
        nthUglyNumber(10);
    }
}
