package Offer.day04;

/**
 * author ye
 * createDate 2022/3/21  19:17
 */
public class Solution03 {
    public int missingNumber(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j){
            int m = (i + j) / 2;
            if (nums[m] == m){
                i = m + 1;
            }else {
                j = m - 1;
            }
        }
        return -1;
    }
}
