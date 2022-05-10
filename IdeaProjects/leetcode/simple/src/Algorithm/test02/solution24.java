package Algorithm.test02;

import org.junit.Test;

public class solution24 {
    public ListNode swapPairs(ListNode head) {
        ListNode head1 = new ListNode();
        head1.next = head;
        ListNode temp = head1;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return head1.next;
    }

    @Test
    public void test() {
        ListNode listNode = null;
        ListNodeTest test = new ListNodeTest();
        listNode = test.createNode(new int[]{1,2,3,4});
        ListNode listNode1 = swapPairs(listNode);
        while (listNode1 != null){
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }
    }
}
