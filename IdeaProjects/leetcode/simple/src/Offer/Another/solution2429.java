package Offer.Another;

import java.util.Stack;

/**
 * author ye
 * createDate 2022/4/28  19:14
 */
public class solution2429 {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (!stack.isEmpty() && stack.peek() > 0 && asteroids[i] < 0){
                if (stack.peek() < -asteroids[i]){
                    stack.pop();
                    i--;
                }else if (stack.peek() == -asteroids[i]){
                    stack.pop();
                }
            }else {
                stack.push(asteroids[i]);
            }
        }
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
