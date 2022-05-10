package Algorithm.test05;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * author ye
 * createDate 2022/2/16  20:02
 */
public class solution84 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int left[] = new int[n];
        int[] right = new int[n];
        //fill(int[] a, int val)
        //将指定的int值分配给指定的int数组的每个元素。
        Arrays.fill(right, n);

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
