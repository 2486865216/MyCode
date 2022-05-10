package Algorithm.test02;

public class solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode test = new ListNode(0);
        test.next = head;

        ListNode previous = test;
        ListNode end = test;
        while (end.next != null){
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode start = previous.next;
            ListNode node = end.next;
            end.next = null;
            previous.next = reverseK(start);
            start.next = node;
            previous = start;
            end = previous;
        }
        return test.next;
    }
    private ListNode reverseK(ListNode head){
        ListNode previous = null;
        ListNode end = head;
        while (end != null){
            ListNode temp = end.next;
            end.next = previous;
            previous = end;
            end = temp;
        }
        return previous;
    }
}
