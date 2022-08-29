package Offer.day12;

import Offer.Utils.ListNode;

/**
 * author ye
 * createDate 2022/3/29  18:10
 */
public class Solution01 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                pre.next = l1;
                pre = pre.next;
                l1 = l1.next;
            }else {
                pre.next = l2;
                pre = pre.next;
                l2 = l2.next;
            }
        }
        if (l1 != null){
            pre.next = l1;
        }
        if (l2 != null){
            pre.next = l2;
        }
        return head.next;
    }
}
