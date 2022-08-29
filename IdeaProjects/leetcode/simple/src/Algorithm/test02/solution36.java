package Algorithm.test02;

import org.junit.Test;

public class solution36 {
    public boolean isValidSudoku(char[][] board) {
        // 整个board有9行，第二维的维数10是为了让下标有9，和数独中的数字9对应。
        int[][] columns = new int[9][10];
        int[][] row = new int[9][10];
        int[][] box = new int[9][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') continue;
                int current = board[i][j] - '0';
                int x = j/3 + (i/3) * 3;
                if (row[i][current] == 1){
                    return false;
                }
                if (columns[j][current] == 1){
                    return false;
                }
                if (box[x][current] == 1){
                    return false;
                }
                row[i][current] = 1;
                columns[j][current] = 1;
                box[x][current] = 1;
            }
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println('3' - '0');
    }
}
