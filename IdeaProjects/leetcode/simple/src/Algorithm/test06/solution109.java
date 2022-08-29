package Algorithm.test06;

/**
 * author ye
 * createDate 2022/2/27  19:15
 */
public class solution109 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode leftNode = head;
        ListNode midNode = leftNode.next;
        ListNode rightNode = midNode.next;
        //1 2 3 4 5 6 7 8 9
        //选择中间或中间左边
        //while (rightNode != null && rightNode.next != null && rightNode.next.next != null)
        //选择中间或中间右边
        while (rightNode != null && rightNode.next != null){
            leftNode = leftNode.next;
            midNode = leftNode.next;
            rightNode = rightNode.next.next;
        }
        leftNode.next = null;
        TreeNode root = new TreeNode(midNode.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(midNode.next);
        return root;
    }
}
