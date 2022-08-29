package Algorithm.test06;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * author ye
 * createDate 2022/3/1  19:07
 */
public class solution111 {
    //public int minDepth(TreeNode root) {
    //    if (root == null) return 0;
    //    if (root.left == null && root.right == null) return 1;
    //    int minDepth = Integer.MAX_VALUE;
    //    if (root.left != null){
    //        minDepth = Math.min(minDepth(root.left), minDepth);
    //    }
    //    if (root.right != null){
    //        minDepth = Math.min(minDepth(root.right), minDepth);
    //    }
    //    return minDepth + 1;
    //}
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int minDepth = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.right == null && current.left == null){
                    return minDepth;
                }
                if (current.left!=null){
                    queue.offer(current.left);
                }
                if (current.right!=null){
                    queue.offer(current.right);
                }
            }
            minDepth++;
        }
        return minDepth;
    }

    @Test
    public void test() {
        System.out.println(minDepth(new TreeNode(1)));
    }
}
