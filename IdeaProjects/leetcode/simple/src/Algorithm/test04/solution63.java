package Algorithm.test04;

/**
 * author ye
 * createDate 2022/1/24  19:52
 */
public class solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] db = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) break;
            db[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) break;
            db[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1){
                    db[i][j] = 0;
                }else{
                    db[i][j] = db[i - 1][j] + db[i][j - 1];
                }
            }
        }
        return db[m - 1][n - 1];
    }
}
