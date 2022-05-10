package Offer.day21;

/**
 * author ye
 * createDate 2022/4/7  19:17
 */
public class Solution02 {
    public int add(int a, int b) {
        int c = 0;
        while (b != 0){
            c = (a & b) << 1;
            a = a ^ b;
            b = c;
        }
        return c;
    }
}
