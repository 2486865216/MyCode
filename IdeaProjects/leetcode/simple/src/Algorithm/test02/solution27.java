package Algorithm.test02;

public class solution27 {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val){
                for (int j = i; j < len - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                i = i - 1;
                len = len -1;
            }
        }
        return len;
    }
}
