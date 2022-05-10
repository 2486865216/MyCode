package Offer.day08;

/**
 * author ye
 * createDate 2022/3/25  18:33
 */
public class Solution01 {
    public int fib(int n) {
        int a = 0;
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
