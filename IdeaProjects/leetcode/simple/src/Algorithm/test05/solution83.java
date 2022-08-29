package Algorithm.test05;

/**
 * author ye
 * createDate 2022/2/15  20:38
 */
public class solution83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode prev = new ListNode();
        prev.next = head;
        while (head.next != null){
            if (head.val == head.next.val){
                head.next = head.next.next;
            }else {
                head = head.next;
            }
        }
        return prev.next;
    }
}
