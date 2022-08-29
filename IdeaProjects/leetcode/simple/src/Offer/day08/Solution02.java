package Offer.day08;

/**
 * author ye
 * createDate 2022/3/25  18:46
 */
public class Solution02 {
    public int numWays(int n) {
        int a = 1;
        int b = 1;
        int sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
