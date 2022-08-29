package Algorithm.test05;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/17  19:09
 */
public class solution86 {
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode();
        ListNode smallHead = small;
        ListNode big = new ListNode();
        ListNode bigHead = big;
        while (head != null){
            if (head.val < x){
                small.next = head;
                small = small.next;
            }else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        big.next = null;
        small.next = bigHead.next;
        return smallHead.next;
    }

    @Test
    public void test() {
        ListNode node6 = new ListNode(2,null);
        ListNode node5 = new ListNode(5,node6);
        ListNode node4 = new ListNode(2,node5);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(4,node3);
        ListNode node1 = new ListNode(1,node2);

        partition(node1, 3);
    }
}
