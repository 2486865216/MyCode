package Offer.day17;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * author ye
 * createDate 2022/4/3  19:51
 */
public class MedianFinder {
    /** initialize your data structure here. */
    Queue<Integer> A;
    Queue<Integer> B;
    public MedianFinder() {
        A = new PriorityQueue<>();
        B = new PriorityQueue<>((x, y) -> y - x);
    }

    public void addNum(int num) {
        if (A.size() != B.size()){
            A.add(num);
            B.add(A.poll());
        }else {
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian() {
        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
    }
}
