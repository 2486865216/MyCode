package Offer.day01;

import org.w3c.dom.Node;

import java.util.Stack;

/**
 * author ye
 * createDate 2022/3/18  19:39
 */
public class MinStack {
    /** initialize your data structure here. */
    /*Node head;
    public MinStack() {
    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x, null);
        }else {
            Node cur = new Node(x, Math.min(x, head.min), head);
            head = cur;
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int min() {
        return head.min;
    }

    private class Node{
        int val;
        int min;
        Node next;

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }*/
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.add(x);
        if (stack2.isEmpty() || stack2.peek() >= x){
            stack2.add(x);
        }
    }

    public void pop() {
        if (stack1.pop().equals(stack2.peek())){
            stack2.pop();
        }
    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        return stack2.peek();
    }
}
