package Algorithm.test02;

public class solution26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int left = 1;
        int right = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[right] != nums[right - 1]){
                nums[left] = nums[right];
                left = left + 1;
            }
            right = right + 1;
        }
        return left;
    }
}
