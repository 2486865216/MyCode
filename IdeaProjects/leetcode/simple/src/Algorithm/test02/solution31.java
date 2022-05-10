package Algorithm.test02;

public class solution31 {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int left = len - 2;
        int right = len - 1;
        int k = len - 1;
        while (left >= 0 && nums[left] >= nums[right]){
            left--;
            right--;
        }
        if (left >= 0){
            while (nums[left] >= nums[k]){
                k--;
            }
            swap(nums, left, k);
        }
        reverse(nums, right, len - 1);
    }
    public void swap(int[] nums, int left, int k){
        int temp;
        temp = nums[left];
        nums[left] = nums[k];
        nums[k] = temp;
    }
    public void reverse(int[] nums, int right, int len){
        while (right < len){
            swap(nums, right++, len--);
        }
    }
}
