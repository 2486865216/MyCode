package Offer.day19;

import Offer.Utils.TreeNode;

/**
 * author ye
 * createDate 2022/4/5  18:41
 */
public class Solution02 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        //二叉搜索树的性质
        if (root.val > q.val && root.val > p.val) return lowestCommonAncestor(root.left, p, q);
        if (root.val < q.val && root.val < p.val) return lowestCommonAncestor(root.right, p, q);

        return root;
    }
}
