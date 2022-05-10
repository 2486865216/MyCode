package Algorithm.test01;

import java.util.HashMap;
import java.util.Map;

public class solution1 {
    public int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                index[0] = i;
                index[1] = map.get(nums[i]);
            }
            map.put(target - nums[i],i);
        }
        return index;
    }
}
