package Offer.Another;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * author ye
 * createDate 2022/4/26  18:20
 */
public class MyStack {

    Queue<Integer> queue = new LinkedList<>();
    public MyStack() {

    }

    public void push(int x) {
        int n = queue.size();
        queue.offer(x);
        for (int i = 0; i < n; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
