package Algorithm.test06;

import java.util.LinkedList;
import java.util.Queue;

/**
 * author ye
 * createDate 2022/3/4  19:44
 */
public class solution116 {
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i < size - 1){
                    cur.next = queue.peek();
                }
                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }
    //public Algorithm.Util.Node connect(Algorithm.Util.Node root) {
    //    if (root == null) return null;
    //    if (root.left != null){
    //        root.left.next = root.right;
    //        if (root.next != null){
    //            root.right.next = root.next.left;
    //        }else {
    //            root.right.next = null;
    //        }
    //    }
    //    connect(root.left);
    //    connect(root.right);
    //    return root;
    //}
}
