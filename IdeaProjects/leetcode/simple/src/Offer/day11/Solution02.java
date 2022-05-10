package Offer.day11;

import Offer.Utils.ListNode;

/**
 * author ye
 * createDate 2022/3/28  18:54
 */
public class Solution02 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode left = head;
        while (head != null){
            if (k == 0){
                left = left.next;
            }else {
                k = k -1;
            }
            head = head.next;
        }
        return left;
    }
}
