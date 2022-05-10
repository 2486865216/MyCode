package Offer.day15;

/**
 * author ye
 * createDate 2022/4/1  18:51
 */
public class Solution02 {
    Node pre, head;
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(Node current){
        if (current == null) return;
        dfs(current.left);
        if (pre == null) {
            head = current;
        }else {
            pre.right = current;
        }
        current.left = pre;
        pre = current;
        dfs(current.right);
    }
}
