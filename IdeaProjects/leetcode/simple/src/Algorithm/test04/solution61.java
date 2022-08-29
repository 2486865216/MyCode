package Algorithm.test04;

import org.junit.Test;

public class solution61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (k ==0 || head == null || head.next == null){
            return head;
        }
        ListNode previous = new ListNode();
        previous.next = head;
        ListNode left = new ListNode();
        ListNode right = new ListNode();
        int x = 1;
        right = head;
        while (right.next != null){
            right = right.next;
            x++;
        }
        for (int i = 0; i < k%x; i++) {
            left = previous;
            right = previous.next;
            while (right.next != null){
                left = left.next;
                right = right.next;
            }
            right.next = previous.next;
            left.next = null;
            previous.next = right;
        }
        return previous.next;
    }

    @Test
    public void test() {
        ListNode test = new ListNode(1);
        ListNode test2 = new ListNode(2);
        ListNode test3 = new ListNode(3);
        ListNode test4 = new ListNode(4);
        ListNode test5 = new ListNode(5);
        test.next = test2;
        test2.next = test3;
        test3.next = test4;
        test4.next = test5;
        ListNode listNode = rotateRight(test, 2);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode rotateRight2(ListNode head, int k) {
        if (k ==0 || head == null || head.next == null){
            return head;
        }
        int x = 1;
        ListNode item = head;
        while (item.next != null){
            item = item.next;
            x++;
        }
        int length = x - k % x;
        if (length == x){
            return head;
        }
        item.next = head;
        while (length-- > 0){
            item = item.next;
        }
        ListNode temp = item.next;
        item.next = null;
        return temp;
    }
}
