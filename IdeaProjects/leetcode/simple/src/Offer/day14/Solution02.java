package Offer.day14;

/**
 * author ye
 * createDate 2022/3/31  19:03
 */
public class Solution02 {
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }

    private int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= m || j >= n || (i % 10 + i /10 + j % 10 + j / 10) > k || visited[i][j]) return 0;
        visited[i][j] = true;
        return dfs(i + 1, j, m, n, k, visited)
                + dfs(i, j + 1, m, n, k, visited) + 1;
    }
}
