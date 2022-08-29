package Offer.day31;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/17  18:39
 */
public class Solution01 {
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        long x = 1;
        int p = 1000000007;
        while (n > 4){
            x = (x * 3) % p;
            n -= 3;
        }
        return (int) ((x * n) % p);
    }
}
