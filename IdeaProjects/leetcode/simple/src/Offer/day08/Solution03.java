package Offer.day08;

/**
 * author ye
 * createDate 2022/3/25  18:52
 */
public class Solution03 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int max = 0;
        int min = prices[0];

        for (int i = 1; i < len; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}
