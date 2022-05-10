package Offer.day24;

/**
 * author ye
 * createDate 2022/4/10  19:32
 */
public class Solution03 {
    public int lastRemaining(int n, int m) {
        int f = 0;
        for (int i = 2; i <= n; i++) {
            f = (m + f) % i;
        }
        return f;
    }
}
