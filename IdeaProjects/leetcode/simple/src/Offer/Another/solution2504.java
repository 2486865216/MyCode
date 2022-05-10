package Offer.Another;

import java.util.HashSet;
import java.util.Set;

/**
 * author ye
 * createDate 2022/4/24  18:48
 */
public class solution2504 {
    public int longestConsecutive(int[] nums) {
        if (nums.length < 1) return nums.length;
        Set<Integer> set = new HashSet<>();
        int longNum = 0;
        int current = 0;
        int currentLong = 0;
        for(int num: nums){
            set.add(num);
        }
        for(Integer num : set){
            if (!set.contains(num - 1)){
                current = num;
                currentLong = 1;
                while (set.contains(current + 1)){
                    current++;
                    currentLong++;
                }
                longNum = Math.max(longNum, currentLong);
            }
        }
        return longNum;
    }
}
