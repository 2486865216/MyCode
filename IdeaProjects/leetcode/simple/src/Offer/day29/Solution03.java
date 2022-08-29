package Offer.day29;

import java.util.Arrays;

/**
 * author ye
 * createDate 2022/4/15  19:12
 */
public class Solution03 {
    public double[] dicesProbability(int n) {
        double[] res = new double[6];
        Arrays.fill(res, 1/ 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tem = new double[5 * i + 1];
            for (int j = 0; j < res.length; j++) {
                for (int k = 0; k < 6; k++) {
                    tem[j + k] += res[j] / 6;
                }
            }
            res = tem;
        }
        return res;
    }
}
