package Offer.Another;

import Offer.Utils.ListNode;
import org.junit.Test;

/**
 * author ye
 * createDate 2022/5/9  14:06
 */
public class solution2482 {
    public ListNode sortList(ListNode head) {
        //return sorted(head);
        if (head == null || head.next == null) return head;
        int len = 0;
        ListNode node = head;
        while (node != null){
            len++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        for (int i = 1; i < len; i <<= 1) {
            ListNode current = dummyHead.next, previous = dummyHead;
            while (current != null){
                ListNode head1 = current;
                for (int j = 1; j < i && current.next != null; j++) {
                    current = current.next;
                }
                ListNode head2 = current.next;
                current.next = null;
                current = head2;
                for (int j = 1; j < i && current != null && current.next != null; j++) {
                    current = current.next;
                }
                ListNode next = null;
                if (current != null){
                    next = current.next;
                    current.next = null;
                }
                ListNode merged = merge(head1, head2);
                previous.next = merged;
                while (previous.next != null){
                    previous = previous.next;
                }
                current = next;
            }
        }
        return dummyHead.next;
    }

    public ListNode sorted(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode slower = head, faster = head;
        while (faster.next != null && faster.next.next != null){
            slower = slower.next;
            faster = faster.next.next;
        }
        ListNode mid = slower.next;
        slower.next = null;
        ListNode list1 = sorted(head);
        ListNode list2 = sorted(mid);
        return merge(list1, list2);

    }

    public ListNode merge(ListNode list1, ListNode list2){
        ListNode list = new ListNode(0);
        ListNode pre = list;
        while (list1 != null && list2 != null){
            if (list1.val <= list2.val){
                list.next = list1;
                list1 = list1.next;
            }else {
                list.next = list2;
                list2 = list2.next;
            }
            list = list.next;
        }
        if (list1 != null){
            list.next = list1;
        }
        if (list2 != null){
            list.next = list2;
        }
        return pre.next;
    }
}
