package Algorithm.test06;

/**
 * author ye
 * createDate 2022/3/4  20:14
 */
public class solution117 {
    public Node connect(Node root) {
        if (root == null) return null;
        Node cur = root;
        while (cur != null){
            Node prev = new Node();
            Node next = prev;
            while (cur != null){
                if (cur.left != null){
                    next.next = cur.left;
                    next = next.next;
                }
                if (cur.right != null){
                    next.next = cur.right;
                    next = next.next;
                }
                cur = prev.next;
            }
        }
        return root;
    }
}
