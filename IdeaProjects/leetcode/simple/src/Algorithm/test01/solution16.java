package Algorithm.test01;

import java.util.Arrays;

public class solution16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int num = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r){
                int num2 = nums[i] + nums[l] + nums[r];
                if (Math.abs(num2 - target) < Math.abs(num - target)){
                    num = num2;
                }
                if (num2 > target){
                    r--;
                }else if (num2 < target){
                    l++;
                }else{
                    return target;
                }
            }
        }
        return num;
    }
}
