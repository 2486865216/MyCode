package Offer.day20;

/**
 * author ye
 * createDate 2022/4/6  18:57
 */
public class Solution02 {
    public double myPow(double x, int n) {
        long b = n;
        if (b < 0){
            b = -b;
            x = 1/x;
        }
        double res = 1.0;
        while (b > 0){
            if ((b & 1) == 1){
                res = res * x;
            }
            b = b >> 1;
            x = x * x;
        }
        return res;
    }
}
