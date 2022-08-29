package Algorithm.test05;

/**
 * author ye
 * createDate 2022/2/19  19:33
 */
public class solution92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode dummyNode = pre;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode currentNode = pre.next;
        ListNode nextNode;
        for (int i = 0; i < right - left; i++) {
            nextNode = currentNode.next;
            currentNode.next = nextNode.next;
            nextNode.next = pre.next;
            pre.next = nextNode;
        }
        return dummyNode.next;
    }
}
