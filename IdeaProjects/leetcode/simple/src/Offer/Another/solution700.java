package Offer.Another;

import Offer.Utils.TreeNode;

/**
 * author ye
 * createDate 2022/4/28  19:38
 */
public class solution700 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        return root.val < val ? searchBST(root.right, val) : searchBST(root.left, val);
    }
}
