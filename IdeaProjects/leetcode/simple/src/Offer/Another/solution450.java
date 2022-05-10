package Offer.Another;

import Offer.Utils.TreeNode;

/**
 * author ye
 * createDate 2022/4/28  19:45
 */
public class solution450 {
    public Integer leftVal(TreeNode root){
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }
    public Integer rightVal(TreeNode root){
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val < key){
            root.right = deleteNode(root.right, key);
        }else if (root.val > key){
            root.left = deleteNode(root.left, key);
        }else {
            if (root.left == null && root.right == null){
                root = null;
            }else if (root.right != null){
                root.val = rightVal(root);
                root.right = deleteNode(root.right, root.val);
            }else {
                root.val = leftVal(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
}
