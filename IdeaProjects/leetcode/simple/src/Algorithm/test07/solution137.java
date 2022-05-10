package Algorithm.test07;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/16  20:03
 */
public class solution137 {
    public int singleNumber(int[] nums) {
        int ans = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            Integer value = integerIntegerEntry.getValue();
            if (value == 1){
                ans = integerIntegerEntry.getKey();
            }
        }
        return ans;
        /*int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int total = 0;
            for (int num: nums) {
                total += ((num >> i) & 1);
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;*/
    }
}
