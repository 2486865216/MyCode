package Offer.day27;

import java.util.*;

/**
 * author ye
 * createDate 2022/4/13  19:13
 */
public class MaxQueue {
    Queue<Integer> queue;
    Deque<Integer> deque;
    public MaxQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }
    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }

    public void push_back(int value) {
        while (!deque.isEmpty() && value > deque.peekLast()){
            deque.pollLast();
        }
        queue.offer(value);
        deque.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()){
            return -1;
        }
        int res = queue.poll();
        if (res == deque.peekFirst()){
            deque.pollFirst();
        }
        return res;
    }
}
