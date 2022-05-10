package Offer.Another;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/4/27  19:30
 */
public class solution560 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)){
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0)  + 1);
        }
        return count;
    }
}
