package Offer.day07;

import Offer.Utils.TreeNode;

/**
 * author ye
 * createDate 2022/3/24  20:00
 */
public class Solution02 {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}
