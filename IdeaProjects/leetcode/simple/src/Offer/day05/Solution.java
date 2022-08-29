package Offer.day05;

/**
 * author ye
 * createDate 2022/3/22  18:52
 */
public class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length;
        int row = 0, col = m - 1;
        while (row < n && col >= 0){
            if (matrix[row][col] == target){
                return true;
            }else if (matrix[row][col] < target){
                row++;
            }else if (matrix[row][col] > target){
                col--;
            }
        }
        return false;
    }
}
