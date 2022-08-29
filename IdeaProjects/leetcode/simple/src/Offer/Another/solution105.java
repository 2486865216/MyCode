package Offer.Another;

/**
 * author ye
 * createDate 2022/5/10  8:20
 */
public class solution105 {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, dfs(grid, i, j));
            }
        }
        return max;
    }
    public int dfs(int[][] grid, int i, int j){
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == 0){
            return 0;
        }
        grid[i][j] = 0;
        return dfs(grid, i + 1, j) + dfs(grid, i - 1, j) + dfs(grid, i, j + 1) + dfs(grid, i, j - 1) + 1;
    }
}
