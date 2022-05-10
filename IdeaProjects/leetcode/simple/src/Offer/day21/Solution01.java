package Offer.day21;

/**
 * author ye
 * createDate 2022/4/7  19:08
 */
public class Solution01 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0){
            res = res + (n & 1);
            n >>>= 1;
        }
        return res;
    }
}
