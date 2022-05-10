package Offer.Another;

/**
 * author ye
 * createDate 2022/4/27  19:12
 */
public class solution695 {
    int max = 0;
    public int maxAreaOfIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, dfs(grid, i, j));
            }
        }
        return max;
    }
    public int dfs(int[][] grid, int i, int j){
        if (j < 0 || i < 0 || i == grid.length || j == grid[0].length || grid[i][j] == 0){
            return 0;
        }
        grid[i][j] = 0;
        return dfs(grid, i + 1, j) + dfs(grid, i, j + 1) + dfs(grid, i - 1, j) + dfs(grid, i, j - 1) + 1;
    }
}
