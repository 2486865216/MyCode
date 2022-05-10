package Offer.day04;

import java.util.HashSet;
import java.util.Set;

/**
 * author ye
 * createDate 2022/3/21  18:43
 */
public class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int flag = -1;
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])){
                flag = nums[i];
                break;
            }
        }
        return flag;
    }
}
