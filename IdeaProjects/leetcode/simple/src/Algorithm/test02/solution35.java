package Algorithm.test02;

public class solution35 {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (right + left) / 2;
        while (left <= right){
            if (nums[mid] < target){
                left = mid + 1;
                mid = (right + left) / 2;
            }else if (nums[mid] > target){
                right = mid - 1;
                mid = (right + left) / 2;
            } else if (nums[mid] == target){
                return mid;
            }
        }
        if (nums[mid] < target){
            return mid + 1;
        }else if (nums[mid] > target && mid > 0){
            return mid - 1;
        }
        return 0;
    }
}
