package Offer.day04;

/**
 * author ye
 * createDate 2022/3/21  18:48
 */
public class Solution02 {
    public int search(int[] nums, int target) {
        //int num = 0;
        //for (int i = 0; i < nums.length; i++) {
        //    if (target == nums[i]){
        //        num++;
        //    }
        //    if (target != nums[i] && num != 0){
        //        break;
        //    }
        //}
        //return num;
        int i = 0;
        int j = nums.length - 1;
        while (i <= j){
            int m = (i + j) / 2;
            if (nums[m] <= target){
                i = m + 1;
            }else {
                j = m - 1;
            }
        }
        int right = i;
        if (j >= 0 && nums[j] != target) return 0;
        i = 0;
        j = nums.length - 1;
        while (i <= j){
            int m = (i + j) / 2;
            if (nums[m] < target){
                i = m + 1;
            }else {
                j = m - 1;
            }
        }
        return right - j - 1;
    }
}
