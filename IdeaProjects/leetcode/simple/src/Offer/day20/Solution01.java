package Offer.day20;

import Offer.Utils.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/4/6  18:13
 */
public class Solution01 {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, inorder, 0, preorder.length - 1 , 0, inorder.length - 1);
    }
    public TreeNode build(int[] preorder, int[] inorder, int pre_left, int pre_right, int ino_left, int ino_right){
        if (pre_left > pre_right) return null;
        int rootVal = pre_left;
        TreeNode root = new TreeNode(preorder[rootVal]);
        int rootIndex = map.get(preorder[rootVal]);
        root.left = build(preorder, inorder, pre_left + 1, pre_left + (rootIndex - ino_left), ino_left, rootIndex - 1);
        root.right = build(preorder, inorder, pre_left + (rootIndex - ino_left) + 1, pre_right, rootIndex + 1, ino_right);
        return root;
    }
}
