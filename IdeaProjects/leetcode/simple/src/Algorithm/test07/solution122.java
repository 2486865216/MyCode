package Algorithm.test07;

/**
 * author ye
 * createDate 2022/3/6  19:34
 */
public class solution122 {
    public int maxProfit(int[] prices) {
        //int n = prices.length;
        //int dp0 = 0;
        //int dp1 =  - prices[0];
        //for (int i = 1; i < n; i++) {
        //    dp0 = Math.max(dp0, dp0 + prices[i]);
        //    dp1 = Math.max(dp1, dp1 - prices[i]);
        //}
        //return dp0;
        //贪心
        int n = prices.length;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            //有利可图就卖
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
