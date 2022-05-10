package Offer.day23;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * author ye
 * createDate 2022/4/9  18:33
 */
public class Solution01 {
    public int majorityElement(int[] nums) {
        /*Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Map.Entry<Integer, Integer> res = null;
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> map1 : entries) {
            if (res == null || map1.getValue() > res.getValue()){
                res = map1;
            }
        }
        return res.getKey();*/
        int res = 0;
        int x = 0;
        for(int num : nums){
            if (res == 0) x = num;
            res += x == num ? 1 : -1;
        }
        return x;
    }
}
