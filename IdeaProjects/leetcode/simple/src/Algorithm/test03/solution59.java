package Algorithm.test03;

public class solution59 {
    public int[][] generateMatrix(int n) {
        int[][] nums = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int numsLength = 1;
        while (numsLength <= n * n){
            for (int i = left; i <= right && numsLength <= n * n; i++) {
                nums[top][i] = numsLength;
                numsLength++;
            }
            top++;
            for (int i = top; i <= bottom & numsLength <= n * n; i++) {
                nums[i][right] = numsLength;
                numsLength++;
            }
            right--;
            for (int i = right; i >= left && numsLength <= n * n; i--) {
                nums[bottom][i] = numsLength;
                numsLength++;
            }
            bottom--;
            for (int i = bottom; i >=top && numsLength <= n * n; i--) {
                nums[i][left] = numsLength;
                numsLength++;
            }
            left++;
        }
        return nums;
    }
}
