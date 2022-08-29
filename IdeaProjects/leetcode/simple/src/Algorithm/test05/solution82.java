package Algorithm.test05;

/**
 * author ye
 * createDate 2022/2/15  19:38
 */
public class solution82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode currentNode = pre;
        while (currentNode.next != null && currentNode.next.next != null){
            if (currentNode.next.val == currentNode.next.next.val){
                int x = currentNode.next.val;
                while (currentNode.next != null && currentNode.next.val == x){
                    currentNode.next = currentNode.next.next;
                }
            }else {
                currentNode = currentNode.next;
            }
        }
        return pre.next;
    }
}
