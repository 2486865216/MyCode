package Offer.Another;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author ye
 * createDate 2022/4/27  20:14
 */
public class solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        int m, n;
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                m = j + 1;
                n = nums.length - 1;
                while (m < n){
                    int sum = nums[i] + nums[j] + nums[m] + nums[n];
                    if (sum > target){
                        n--;
                    } else if (sum < target){
                        m++;
                    } else {
                        lists.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                        while (m < n && nums[n] == nums[n - 1]) n--;
                        while (m < n && nums[m] == nums[m + 1]) m++;
                        m++;
                        n--;
                    }
                }
            }
        }
        return lists;
    }
}
