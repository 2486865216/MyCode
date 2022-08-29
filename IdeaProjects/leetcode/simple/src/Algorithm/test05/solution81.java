package Algorithm.test05;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/14  20:18
 */
public class solution81 {
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return false;
        if (n == 1) return nums[0]==target;
        int left = 0;
        int right = n - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] == target){
                return true;
            }
            if (nums[left] == nums[mid] && nums[mid] == nums[right]){
                left++;
                right--;
            }else if (nums[left] <= nums[mid]){
                if (nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else{
                if (nums[mid] < target && target <= nums[n - 1]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    @Test
    public void test() {
        search(new int[]{2,5,6,0,0,1,2}, 3);
    }
}
