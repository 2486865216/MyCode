package Offer.day07;

import Offer.Utils.TreeNode;
import org.junit.Test;

/**
 * author ye
 * createDate 2022/3/24  20:26
 */
public class Solution03 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return true;
        return dfs(root, root);
    }

    public boolean dfs(TreeNode q, TreeNode p){
        if (q == null && p == null) return true;
        if (q == null && p != null) return false;
        if (q != null && p == null) return false;
        return q.val == p.val && dfs(q.left, p.right) && dfs(q.right, p.left);
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        TreeNode left1 = new TreeNode(3);
        TreeNode right1 = new TreeNode(3);
        root.left = left;
        root.right = right;
        left.left = null;
        left.right = left1;
        right.left = null;
        right.right = right1;

        isSymmetric(root);
    }
}
