package Algorithm.test08;

import Offer.Utils.ListNode;

/**
 * author ye
 * createDate 2022/4/19  10:10
 */
public class Solution148 {
    public ListNode sortList(ListNode head) {
        //return sort(head, null);
        if (head == null || head.next ==null) return head;
        ListNode slower = head, faster = head;
        while (faster.next != null && faster.next.next != null){
            slower = slower.next;
            faster = faster.next.next;
        }
        ListNode mid = slower.next;
        slower.next = null;
        ListNode head1 = sortList(head);
        ListNode head2 = sortList(mid);
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead;
        while (head1 != null && head2 != null){
            if (head1.val <= head2.val) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        if (head1 != null){
            temp.next = head1;
        }else if (head2 != null){
            temp.next = head2;
        }
        return dummyHead.next;
    }

    public ListNode sort(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slower = head, faster = head;
        while (faster != tail) {
            slower = slower.next;
            faster = faster.next;
            if (faster != tail) {
                faster = faster.next;
            }
        }
        ListNode mid = slower;
        ListNode list1 = sort(head, mid);
        ListNode list2 = sort(mid, tail);
        ListNode sored = merge(list1, list2);
        return sored;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}
