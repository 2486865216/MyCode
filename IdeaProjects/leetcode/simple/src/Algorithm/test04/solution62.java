package Algorithm.test04;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/1/24  19:16
 */
public class solution62 {
    public int uniquePaths(int m, int n) {
        int[][] db = new int[m][n];
        for (int i = 0; i < m; i++) {
            db[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            db[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                db[i][j] = db[i - 1][j] + db[i][j - 1];
            }
        }
        return db[m - 1][n - 1];
    }

    @Test
    public void test() {
        uniquePaths(3,2);
    }
}
