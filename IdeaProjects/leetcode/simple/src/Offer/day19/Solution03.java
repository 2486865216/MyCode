package Offer.day19;

import Offer.Utils.TreeNode;

/**
 * author ye
 * createDate 2022/4/5  18:53
 */
public class Solution03 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null){
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null){
            return right;
        }

        return null;
    }
}
