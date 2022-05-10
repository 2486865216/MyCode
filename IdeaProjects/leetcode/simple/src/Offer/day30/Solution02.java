package Offer.day30;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/16  18:47
 */
public class Solution02 {
    int count = 0;
    public int reversePairs(int[] nums) {
        if (nums.length < 2) return 0;
        sort(nums, 0, nums.length - 1, new int[nums.length]);
        return count;
    }
    public void sort(int[] nums, int left, int right, int[] tmp){
        if (left >= right) return;
        int mid = (right - left) / 2 + left;

        sort(nums, left, mid, tmp);

        sort(nums, mid + 1, right, tmp);

        merged(nums, left, mid, right, tmp);
    }
    public void merged(int[] nums, int left, int mid, int right, int[] tmp){
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right){
            if (nums[i] <= nums[j]){
                tmp[index++] = nums[i++];
            }else {
                tmp[index++] = nums[j++];
                count = count + mid - i + 1;
            }
        }
        while (i <= mid){
            tmp[index++] = nums[i++];
        }
        while (j <= right){
            tmp[index++] = nums[j++];
        }

        index = 0;
        while (left <=right){
            nums[left++] = tmp[index++];
        }
    }
}
