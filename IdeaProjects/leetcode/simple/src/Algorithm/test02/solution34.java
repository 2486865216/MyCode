package Algorithm.test02;

import org.junit.Test;

public class solution34 {
    public int[] searchRange(int[] nums, int target) {
        int[] m = {-1, -1};
        int left = 0;
        int right = nums.length - 1;
        int mid = (right + left) / 2;
        while (left <= right){
            if (nums[mid] < target){
                left = mid + 1;
                mid = (right + left) / 2;
            }else if (nums[mid] > target){
                right = mid - 1;
                mid = (right + left) / 2;
            }else {
                int temp = mid;
                m[0] = mid;
                m[1] = mid;
                while (mid - 1 >= 0 && nums[mid] == nums[mid - 1]){
                    m[0] = mid - 1;
                    mid = mid - 1;
                }
                while (temp + 1 <= nums.length-1 && nums[temp] == nums[temp + 1]){
                        m[1] = temp + 1;
                        temp = temp + 1;
                }
                break;
            }
        }
        return m;
    }

    @Test
    public void test() {
        int[] nums = {1,2,3,4};
        int[] m = searchRange(nums,3);
        System.out.println(m[0]+""+m[1]);
    }
}
