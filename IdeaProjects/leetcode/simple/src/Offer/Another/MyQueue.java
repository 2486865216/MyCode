package Offer.Another;

import java.util.Stack;

/**
 * author ye
 * createDate 2022/4/26  18:12
 * solution232
 */
public class MyQueue {
    private Stack<Integer> stackA = new Stack<>();
    private Stack<Integer> stackB = new Stack<>();
    Integer front = 0;
    public MyQueue() {

    }

    public void push(int x) {
        stackA.push(x);
        front = x;
    }

    public int pop() {
        if (stackB.isEmpty()){
            while (!stackA.isEmpty()){
                stackB.push(stackA.pop());
            }
        }
        return stackB.pop();
    }

    public int peek() {
        if (stackB.isEmpty()){
            return front;
        }
        return stackB.peek();
    }

    public boolean empty() {
        return stackA.isEmpty() && stackB.isEmpty();
    }
}
