package Offer.day19;

/**
 * author ye
 * createDate 2022/4/5  18:37
 */
public class Solution01 {
    public int sumNums(int n) {
        if (n == 0) return 0;
        return sumNums(n - 1) + n;
    }
}
