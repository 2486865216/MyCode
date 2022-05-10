package Offer.day30;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/16  18:19
 */
public class Solution01 {
    char[] num;
    int[] ans;
    int count = 0, n;

    public int[] printNumbers(int n) {
        //int m = (int) Math.pow(10, n);
        //int[] num = new int[m];
        //for (int i = 0; i < m; i++) {
        //    num[i] = i + 1;
        //}
        //return num;

        this.n = n;
        num = new char[n];
        ans = new int[(int) (Math.pow(10, n) - 1)];
        dfs(0);
        return ans;
    }
    private void dfs(int n) {
        if (n == this.n) {
            String tmp = String.valueOf(num);
            int curNum = Integer.parseInt(tmp);
            if (curNum != 0) ans[count++] = curNum;
            return;
        }
        for (char i = '0'; i <= '9'; i++) {
            num[n] = i;
            dfs(n + 1);
        }
    }
}
