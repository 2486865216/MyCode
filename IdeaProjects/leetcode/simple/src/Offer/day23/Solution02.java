package Offer.day23;

/**
 * author ye
 * createDate 2022/4/9  19:03
 */
public class Solution02 {
    public int[] constructArr(int[] a) {
        int[] num = new int[a.length];
        int n = a.length;

        //a[i]左边的乘积
        for (int i = 0, left = 1; i < n; i++) {
            num[i] = left;
            left *= a[i];
        }

        ///a[i]左边的乘积 * a[i]右边的乘积
        for (int i = n - 1, right = 1; i >= 0; i--) {
            num[i] *= right;
            right *= a[i];
        }
        return num;
    }
}
