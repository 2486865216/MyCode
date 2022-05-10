package Algorithm.test01;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode headNode = new ListNode(0);
        headNode.next = head;
        ListNode leftNode = headNode;
        ListNode rightNode = headNode;
        for (int i = 0; i < n + 1; i++) {
            rightNode = rightNode.next;
        }
        while (rightNode != null){
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }
        ListNode deleteNode = leftNode.next;
        leftNode.next = deleteNode.next;
        return headNode.next;
    }
}
