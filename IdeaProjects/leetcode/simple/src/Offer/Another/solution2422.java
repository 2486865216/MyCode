package Offer.Another;

import Offer.Utils.ListNode;

/**
 * author ye
 * createDate 2022/5/8  19:06
 */
public class solution2422 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = head;
        ListNode node = pre;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        while (temp != null){
            pre = pre.next;
            temp = temp.next;
        }
        pre.next = pre.next.next;
        return node.next;
    }
}
