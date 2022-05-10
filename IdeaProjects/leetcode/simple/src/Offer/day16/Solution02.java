package Offer.day16;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * author ye
 * createDate 2022/4/2  18:50
 */
public class Solution02 {
    public boolean isStraight(int[] nums) {
        /*Set<Integer> set = new HashSet<>();
        int max = 0, min = 14;
        for (int i = 0; i < 5; i++) {
            if (nums[i] == 0) continue;
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
            if (set.contains(nums[i])) break;
            set.add(nums[i]);
        }
        return max - min < 5;*/
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) count++;
            else if (nums[i] == nums[i + 1]) return false;
        }
        return nums[4] - nums[count] < 5;
    }
}
