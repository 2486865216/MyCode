package Algorithm.test05;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * author ye
 * createDate 2022/2/16  20:51
 */
public class solution85 {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        //记录左边连续的一，（矩阵的高）
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1'){
                    left[i][j] = (j==0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] up = new int[m];
            int[] down = new int[m];
            Deque<Integer> stack = new ArrayDeque<>();
            Arrays.fill(down, m);
            for (int j = 0; j < m; j++) {
                while (!stack.isEmpty() && left[stack.peek()][i] >= left[j][i]){
                    down[stack.peek()] = j;
                    stack.pop();
                }
                up[j] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(j);
            }
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, (down[j] - up[j] - 1) * left[j][i]);
            }
        }
        return ans;
    }
}
