package Offer.day25;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * author ye
 * createDate 2022/4/11  19:02
 */
public class Solution02 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int x = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int push : pushed) {
            stack.push(push);
            while (x < popped.length && !stack.isEmpty() && popped[x] == stack.pop()){
                x++;
            }
        }
        return x == popped.length;
    }
}
