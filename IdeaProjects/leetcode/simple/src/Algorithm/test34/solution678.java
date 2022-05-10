package Algorithm.test34;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/24  18:26
 */
public class solution678 {
    public int findLengthOfLCIS(int[] nums) {
        int max = 1;
        int left = 0,right = 0;
        while (right < nums.length - 1){
            if (nums[right] < nums[right + 1]){
                right++;
            }else {
                max = Math.max(max, right - left + 1);
                left = right + 1;
                right++;
            }
        }
        return Math.max(max, right - left + 1);
    }

    @Test
    public void test() {
        findLengthOfLCIS(new int[]{1,3,5,7});
    }
}
