package Algorithm.test09;

import org.junit.Test;

import java.util.Arrays;

/**
 * author ye
 * createDate 2022/4/7  19:22
 */
public class solution167 {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int n = numbers[left] + numbers[right];
            if (n > target) {
                right--;
            } else if (n < target) {
                left++;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[2];
    }
}
