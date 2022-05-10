package Offer.Another;

import java.util.Arrays;

/**
 * author ye
 * createDate 2022/4/29  20:14
 */
public class solution322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = amount + 1;
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i)
                    dp[i] = Math.min(dp[i - coins[j]], dp[i]);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
