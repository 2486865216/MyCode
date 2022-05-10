package Algorithm.test01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> test = new ArrayList<>();
        if (nums.length < 4){
            return test;
        }
        Arrays.sort(nums);
        int m, n;
        for (int i = 0; i < nums.length-3; i++) {
            if (i > 0 && nums[i]==nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length-2; j++) {
                if (j > i + 1 && nums[j]==nums[j - 1]) continue;
                m = j + 1;
                n = nums.length - 1;
                while (m < n){
                    int num4 = nums[n] + nums[m] + nums[i] + nums[j];
                    if (num4 < target){
                        m++;
                    }else if (num4 > target){
                        n--;
                    }else{
                        test.add(Arrays.asList(nums[m], nums[n], nums[i], nums[j]));
                        while (m < n && nums[m] == nums[m + 1]){
                            m++;
                        }
                        while (m < n && nums[n] == nums[n - 1]){
                            n--;
                        }
                        m++;
                        n--;
                    }
                }
            }
        }
        return test;
    }

    @Test
    public void test() {
        System.out.println(fourSum(new  int[]{1,0,-1,0,-2,2}, 0));
    }
}
