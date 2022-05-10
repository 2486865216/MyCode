package Offer.day14;

/**
 * author ye
 * createDate 2022/3/31  18:23
 */
public class Solution01 {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] border, char[] words, int i, int j, int k){
        if (i < 0 || j < 0 || i > border.length - 1 || j > border[0].length - 1 || border[i][j] != words[k]) return false;
        if (k == (words.length - 1)) return true;
        border[i][j] = '\0';
        boolean res = dfs(border, words, i, j + 1, k + 1) || dfs(border, words, i, j - 1, k + 1) ||
                dfs(border, words, i + 1, j, k + 1) || dfs(border, words, i - 1, j, k + 1);
        border[i][j] = words[k];
        return res;
    }
}
