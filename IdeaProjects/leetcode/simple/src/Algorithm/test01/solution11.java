package Algorithm.test01;

public class solution11 {
    public int maxArea(int[] height) {
        int maxHeight = 0;
        int left = 0;
        int right = height.length-1;
        for (int i = 0; i < height.length; i++) {
            if (maxHeight < (right - left) * Math.min(height[left],  height[right])){
                maxHeight = (right - left) * Math.min(height[left],  height[right]);
            }
            if (height[left] < height[right]) {
                left++;
            }else{
                right--;
            }
        }
        return maxHeight;
    }
}
