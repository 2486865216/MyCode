package Offer.day17;


import org.junit.Test;

import java.util.Arrays;

/**
 * author ye
 * createDate 2022/4/3  18:26
 */
public class Solution01 {
    /*public int[] getLeastNumbers(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }

    public void quickSort(int[] nums, int left, int right){
        if (left >= right) return;
        int i = left, j = right;
        while (i < j){
            System.out.println(nums[i]);
            System.out.println(nums[left]);
            while (i < j && nums[j] >= nums[left]) j--;
            while (i < j && nums[i] <= nums[left]) i++;
            swap(nums, i, j);
        }
        swap(nums, left, i);
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    public void swap(int[] nums,int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }*/
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) return arr;

        return quickSort(arr, 0, arr.length - 1, k);
    }
    public int[] quickSort(int[] nums, int left, int right, int k){
        int i = left, j = right;
        while (i < j){
            while (i < j && nums[j] >= nums[left]) j--;
            while (i < j && nums[i] <= nums[left]) i++;
            swap(nums, i, j);
        }
        swap(nums, left, i);
        if (i > k) return quickSort(nums, left, i - 1, k);
        if (i < k) return quickSort(nums, i + 1, right, k);
        return Arrays.copyOf(nums, k);
    }

    public void swap(int[] nums,int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
