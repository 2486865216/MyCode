package Algorithm.test04;

/**
 * author ye
 * createDate 2022/2/11  19:20
 */
public class solution73 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean columnZero = false;
        boolean rowZero = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0){
                columnZero = true;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0){
                rowZero = true;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0){
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        if (columnZero){
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (rowZero){
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
    }
}
