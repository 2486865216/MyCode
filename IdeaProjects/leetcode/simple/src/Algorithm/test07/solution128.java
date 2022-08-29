package Algorithm.test07;

import java.util.HashSet;
import java.util.Set;

/**
 * author ye
 * createDate 2022/3/9  20:18
 */
public class solution128 {
    public int longestConsecutive(int[] nums) {
        int longest = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (Integer integer : set) {
            if (!set.contains(integer - 1)){
                int currentNum = integer;
                int currentLong  = 1;
                while (set.contains(currentNum + 1)){
                    currentNum ++;
                    currentLong++;
                }
                longest = Math.max(longest, currentLong);
            }
        }
        return longest;
    }
}
