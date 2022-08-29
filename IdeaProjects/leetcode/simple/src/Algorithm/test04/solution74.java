package Algorithm.test04;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/11  19:44
 */
public class solution74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        int n = matrix[0].length;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (matrix[middle / n][middle % n] > target) {
                right = middle - 1;
            } else if (matrix[middle / n][middle % n] < target) {
                left = middle + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(searchMatrix(new int[][]{{1,2,3},{4,5,6},{7,8,9}},5));
    }
}
