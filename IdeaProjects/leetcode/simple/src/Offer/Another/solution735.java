package Offer.Another;

import org.junit.Test;

import java.util.Stack;

/**
 * author ye
 * createDate 2022/4/26  18:59
 */
public class solution735 {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (!stack.isEmpty() && stack.peek() > 0 && asteroids[i] < 0){
                if (stack.peek() < -asteroids[i]){
                    stack.pop();
                    i--;
                }else if(stack.peek() == -asteroids[i]){
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

    @Test
    public void test() {
        asteroidCollision(new int[]{-2,-2,1,-2});
    }
}
