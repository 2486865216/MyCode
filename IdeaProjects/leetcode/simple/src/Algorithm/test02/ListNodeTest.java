package Algorithm.test02;


public class ListNodeTest {
    public ListNode createNode(int[] a){
        ListNode headNode = new ListNode();
        ListNode head = headNode;
        for (int i = 0; i < a.length; i++) {
            ListNode listNode = new ListNode();
            listNode.val = a[i];
            headNode.next = listNode;
            headNode = headNode.next;
        }
        return head.next;
    }
}
