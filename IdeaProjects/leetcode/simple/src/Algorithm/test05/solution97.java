package Algorithm.test05;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/21  19:15
 */
public class solution97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int t = s3.length();
        if (n + m != t) return false;
        //boolean[][] isTrue = new boolean[n + 1][m + 1];
        //isTrue[0][0] = true;
        //for (int i = 0; i <= n; i++) {
        //    for (int j = 0; j <= m; j++) {
        //        int p = i + j - 1;
        //        if (i > 0){
        //            isTrue[i][j] |= isTrue[i - 1][j] && s3.charAt(p) == s1.charAt(i - 1);
        //        }
        //        if (j > 0){
        //            isTrue[i][j] |= isTrue[i][j - 1] && s3.charAt(p) == s2.charAt(j - 1);
        //        }
        //    }
        //}
        //return isTrue[n][m];
        boolean[] isTrue = new boolean[m + 1];
        isTrue[0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0){
                    isTrue[j] = isTrue[j] && s3.charAt(p) == s1.charAt(i - 1);
                }
                if (j > 0){
                    isTrue[j] = isTrue[j] || (isTrue[j - 1] && s3.charAt(p) == s2.charAt(j - 1));
                }
            }
        }
        return isTrue[m];
    }

    @Test
    public void test() {
        isInterleave("1","2","12");
    }
}
