package Offer.Another;

import Offer.Utils.ListNode;
import org.junit.Test;

/**
 * author ye
 * createDate 2022/4/18  19:01
 */
public class Solution2511 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0, n1, n2, sum = 0;
        while (l1 != null || l2 != null){
            n1 = l1 != null ? l1.val : 0;
            n2 = l2 != null ? l2.val : 0;
            sum = n1 + n2 + carry;
            if (head == null){
                head = tail = new ListNode(sum % 10);
            }else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0){
            tail.next = new ListNode(carry);
        }
        return head;
    }
}
