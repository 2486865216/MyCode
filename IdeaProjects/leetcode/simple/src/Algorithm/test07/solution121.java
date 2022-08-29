package Algorithm.test07;

/**
 * author ye
 * createDate 2022/3/6  19:13
 */
public class solution121 {
    public int maxProfit(int[] prices) {
        int m = prices.length;
        if (m == 1) return 0;
        int max = 0;
        int min = prices[0];
        //for (int i = 1; i < m; i++) {
        //    max = Math.max(max, prices[i] - min);
        //    min = Math.min(min, prices[i]);
        //}
        for (int i = 0; i < m; i++) {
            if (max < prices[i] - min){
                max = prices[i] - min;
            }
            if (min > prices[i]){
                min = prices[i];
            }
        }
        return max;
    }
}
