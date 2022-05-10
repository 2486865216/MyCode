package Algorithm.test01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[0]>0){
                return lists;
            }
            //去重
            if (i > 0 && nums[i]==nums[i-1]){
                    continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0){
                    right--;
                } else if (sum < 0){
                    left++;
                } else {
                    lists.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //去重
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[right] == nums[left + 1]) left++;

                    left++;
                    right--;
                }
            }
        }
        return lists;
    }
}
