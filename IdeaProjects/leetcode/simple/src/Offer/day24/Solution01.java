package Offer.day24;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/10  18:34
 */
public class Solution01 {
    public int cuttingRope(int n) {
        if  (n <= 3) return n - 1;
        int x = n / 3;
        int y = n % 3;
        int res = 1;
        for (int i = 0; i < x; i++) {
            if (i < x - 1){
                res = res * 3;
            }else {
                if (y == 0){
                    res = res * 3;
                }else if (y == 1){
                    res = res * 4;
                }else {
                    res = res * 2 * 3;
                }
            }
        }
        return res;
    }

    @Test
    public void test() {
        cuttingRope(5);
    }
}
