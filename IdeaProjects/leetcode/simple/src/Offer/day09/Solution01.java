package Offer.day09;

/**
 * author ye
 * createDate 2022/3/26  18:57
 */
public class Solution01 {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int pre = 0;
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        return max;
    }
}
