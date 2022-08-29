package Offer.day24;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author ye
 * createDate 2022/4/10  19:00
 */
public class Solution02 {
    public int[][] findContinuousSequence(int target) {
        int left = 1;
        int right = 1;
        int sum = 0;
        List<int[]> list = new ArrayList<>();
        while (right <= target / 2 + 1){
            sum += right;
            while (sum > target){
                sum -= left;
                left++;
            }
            if (sum == target){
                int[] array = new int[right - left + 1];
                for (int i = 0; i < right - left + 1; i++) {
                    array[i] = left + i;
                }
                list.add(array);
            }
            right++;
        }
        return list.toArray(new int[list.size()][]);
    }

    @Test
    public void test() {
        findContinuousSequence(5);
    }
}
