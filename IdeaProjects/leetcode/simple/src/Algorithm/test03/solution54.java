package Algorithm.test03;

import java.util.ArrayList;
import java.util.List;

public class solution54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int nums = matrix[0].length * matrix.length;
        while (nums > 0){
            for (int i = left; i <= right && nums > 0; i++) {
                list.add(matrix[top][i]);
                nums--;
            }
            top++;
            for (int i = top; i <= bottom & nums > 0; i++) {
                list.add(matrix[i][right]);
                nums--;
            }
            right--;
            for (int i = right; i >= left && nums > 0; i--) {
                list.add(matrix[bottom][i]);
                nums--;
            }
            bottom--;
            for (int i = bottom; i >=top && nums > 0; i--) {
                list.add(matrix[i][left]);
                nums--;
            }
            left++;
        }
        return list;
    }
}
