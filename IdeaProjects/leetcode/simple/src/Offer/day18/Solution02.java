package Offer.day18;

import Offer.Utils.TreeNode;

/**
 * author ye
 * createDate 2022/4/4  18:56
 */
public class Solution02 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode root){
        if (root == null) return 0;
        else return Math.max(height(root.left), height(root.right)) + 1;
    }
}
