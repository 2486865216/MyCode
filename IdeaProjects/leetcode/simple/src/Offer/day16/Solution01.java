package Offer.day16;

import org.junit.Test;

import java.util.Arrays;

/**
 * author ye
 * createDate 2022/4/2  18:33
 */
public class Solution01 {
    public String minNumber(int[] nums) {
        StringBuilder res = new StringBuilder();
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(str, (x, y) -> (x + y).compareTo(y + x));
        for (int i = 0; i < str.length; i++) {
            res.append(str[i]);
        }
        return res.toString();
    }
}
