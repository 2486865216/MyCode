package Offer.day25;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/11  18:34
 */
public class Solution01 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] num = new int[m * n];
        int index = 0,left = 0, right = n - 1, top = 0, bottom = m - 1;
        while (left <= right && top <= bottom){
            for (int i = left; i <= right; i++) {
                num[index] = matrix[top][i];
                index++;
            }
            for (int i = top + 1; i <= bottom; i++) {
                num[index] = matrix[i][right];
                index++;
            }
            if (left < right && top < bottom){
                for (int i = right - 1; i >= left ; i--) {
                    num[index] = matrix[bottom][i];
                    index++;
                }
                for (int i = bottom - 1; i > top; i--) {
                    num[index] = matrix[i][left];
                    index++;
                }
            }
            top++;
            left++;
            right--;
            bottom--;
        }
        return num;
    }

    @Test
    public void test() {
        spiralOrder(new int[][]{{1,2,3},{1,2,3},{1,2,3}});
    }
}
