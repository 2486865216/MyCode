package Offer.day13;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/3/30  18:44
 */
public class Solution02 {
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right){
            if ((nums[left] + nums[right]) < target){
                left--;
            }else if((nums[left] + nums[right]) > target){
                right++;
            }else {
                return new int[]{nums[left], nums[right]};
            }
        }
        return new int[2];
    }
}
