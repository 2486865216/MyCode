package Offer.day05;

/**
 * author ye
 * createDate 2022/3/22  19:19
 */
public class Solution02 {
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (numbers[mid] < numbers[right]){
                right = mid;
            }else if (numbers[mid] > numbers[right]){
                left = mid + 1;
            }else {
                right--;
            }
        }
        return numbers[left];
    }
}
