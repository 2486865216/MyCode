package Algorithm.test03;

import org.junit.Test;

public class solution53 {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int pre = 0;
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            max = Math.max(pre, max);
        }
        return max;
    }

    public int maxSubArray2(int[] nums){
        int max = nums[0];
        int numsSize = nums.length;
        for (int i = 0; i < numsSize; i++)
        {
            int sum = 0;
            for (int j = i; j < numsSize; j++)
            {
                sum += nums[j];
                if (sum > max)
                {
                    max = sum;
                }
            }
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
