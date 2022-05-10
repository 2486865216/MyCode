package Algorithm.test08;

/**
 * author ye
 * createDate 2022/4/19  12:07
 */
public class Solution143 {
    public void reorderList(ListNode head) {
        ListNode faster = head,slower = head;
        while (faster.next != null && faster.next.next != null){
            slower = slower.next;
            faster = faster.next.next;
        }
        ListNode mid = slower.next;
        slower.next = null;
        ListNode pre = null,cur = mid,next = null;
        while (cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        ListNode list1, list2;
        while (head != null && pre != null){
            list1 = head.next;
            list2 = pre.next;

            head.next = pre;
            head = list1;

            pre.next = head;
            pre = list2;
        }
    }
}
