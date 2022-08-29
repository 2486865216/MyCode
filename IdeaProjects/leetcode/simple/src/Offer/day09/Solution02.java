package Offer.day09;

/**
 * author ye
 * createDate 2022/3/26  19:41
 */
public class Solution02 {
    public int maxValue(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0){
                    continue;
                }
                if (i == 0){
                    grid[i][j] += grid[i][j - 1];
                }else if (j == 0){
                    grid[i][j] += grid[i - 1][j];
                }else {
                    grid[i][j] += Math.max(grid[i- 1][j], grid[i][j]);
                }
            }
        }

        return grid[n -1][m - 1];
    }
}
