package Offer.Another;

/**
 * author ye
 * createDate 2022/4/30  19:29
 */
public class solution714 {
    public int maxProfit(int[] prices, int fee) {
        //int sell = 0;
        //int buy = -prices[0];
        //for (int i = 0; i < prices.length; i++) {
        //    sell = Math.max(sell, buy + prices[i] - fee);
        //    buy = Math.max(buy, sell - prices[i]);
        //}
        //return sell;
        int ans = 0;
        int buy = prices[0] + fee;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] + fee < buy){
                buy = prices[i] + fee;
            }else if (prices[i] > buy){
                ans += prices[i] - buy;
                buy = prices[i];
            }
        }
        return ans;
    }
}
