package Algorithm.test04;

/**
 * author ye
 * createDate 2022/1/24  20:10
 */
public class solution64 {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int[][] db = new int[row][column];
        db[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            db[i][0] = db[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < column; j++) {
            db[0][j] = db[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i <row ; i++) {
            for (int j = 1; j < column; j++) {
                db[i][j] = Math.min(db[i - 1][j], db[i][j - 1]) + grid[i][j];
            }
        }
        return db[row - 1][column - 1];
    }
}
