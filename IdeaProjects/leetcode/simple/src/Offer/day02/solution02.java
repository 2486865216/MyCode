package Offer.day02;

import Offer.Utils.ListNode;

/**
 * author ye
 * createDate 2022/3/19  19:29
 */
public class solution02 {
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode cur = head;
        ListNode next = head.next;
        ListNode prev = head;
        while (next != null){
            cur.next = next.next;
            next.next = prev;
            prev = next;
            next = cur.next;
        }
        return prev;
    }
}
