package Offer.day13;

/**
 * author ye
 * createDate 2022/3/30  18:29
 */
public class Solution01 {
    public int[] exchange(int[] nums) {
        if (nums.length < 2) return nums;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            right--;
            left++;
        }
        return nums;
    }
}
