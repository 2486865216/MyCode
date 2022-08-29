package Offer.Another;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/29  19:31
 */
public class solution34 {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] ans = {-1, -1};
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else {
                left = mid;
                right = mid;
                while (left >= 1 && nums[left] == nums[left - 1]) left--;
                while (right < nums.length -1 && nums[right] == nums[right + 1]) right++;
                ans[0] = left;
                ans[1] = right;
                break;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        searchRange(new int[]{1}, 1);
    }
}
