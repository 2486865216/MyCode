package Algorithm.test08;

/**
 * author ye
 * createDate 2022/4/19  18:51
 */
public class Solution147 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastNode = head, curNode = head.next, pre = null;
        while (curNode != null){
            if (lastNode.val > curNode.val){
                pre = dummyHead;
                while (pre.next.val <= curNode.val){
                    pre = pre.next;
                }
                lastNode.next = curNode.next;
                curNode.next = pre.next;
                pre.next = curNode;
            }else {
                lastNode = lastNode.next;
            }
            curNode = lastNode.next;
        }
        return dummyHead.next;
    }
}
