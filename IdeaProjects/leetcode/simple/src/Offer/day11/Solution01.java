package Offer.day11;

import Offer.Utils.ListNode;

/**
 * author ye
 * createDate 2022/3/28  18:37
 */
public class Solution01 {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode current = pre;
        while (current.next != null){
            if (current.next.val == val){
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
        return pre.next;
    }
}
