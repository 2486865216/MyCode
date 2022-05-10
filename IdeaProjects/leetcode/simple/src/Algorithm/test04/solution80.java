package Algorithm.test04;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/14  19:37
 */
public class solution80 {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;
        int left = 2;
        int right = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[left - 2] != nums[right]){
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left;
    }

    @Test
    public void test() {
        int[] a = new int[]{0,0,1,1,1,1,2,3,3};
        int i = removeDuplicates(a);
        System.out.println(i);
    }
}
