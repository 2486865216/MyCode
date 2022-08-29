package Algorithm.test06;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/2/25  18:46
 */
public class solution105 {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right){
        if (preorder_left > preorder_right){
            return null;
        }
        int preorder_root = preorder_left;
        int inorder_root = map.get(preorder[preorder_root]);
        int sub_root = inorder_root - inorder_left;
        TreeNode root = new TreeNode(preorder[preorder_root]);
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + sub_root, inorder_left, inorder_root - 1);
        root.right = myBuildTree(preorder, inorder, preorder_left + 1 + sub_root, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }
}
