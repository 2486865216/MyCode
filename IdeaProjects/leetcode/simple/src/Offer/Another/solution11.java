package Offer.Another;

/**
 * author ye
 * createDate 2022/4/24  18:59
 */
public class solution11 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right){
            if (height[left] <= height[right]){
                max = Math.max(max, height[left] * (right - left));
                left++;
            }else {
                max = Math.max(max, height[right] * (right - left));
                right--;
            }
        }
        return max;
    }
}
