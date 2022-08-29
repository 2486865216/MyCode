package Algorithm.test04;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/1/26  20:40
 */
public class solution70 {
    public int climbStairs(int n) {
        //n = n - 1 + n - 1
        int sum = 1;
        int n1 = 0;
        int n2 = 0;
        for (int i = 1; i <= n; i++) {
            n1 = n2;
            n2 = sum;
            sum = n1 + n2;
        }
        return sum;
    }

    @Test
    public void test() {
        int i = climbStairs(1);
        System.out.println(i);
    }
}
