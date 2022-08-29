package Algorithm.test06;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/2/25  19:30
 */
public class solution106 {
    Map<Integer, Integer> map = new HashMap<>();
    int[] afterNode;
    int len;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        afterNode = postorder;
        len = postorder.length - 1;
        int n =  postorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return myBuildTreeNode(0, n - 1);
    }

    private TreeNode myBuildTreeNode(int left, int right) {
        if (left > right) return null;
        int index = map.get(afterNode[len]);
        TreeNode root = new TreeNode(afterNode[len]);
        len--;
        root.right = myBuildTreeNode(index + 1, right);
        root.left = myBuildTreeNode(left, index - 1);
        return root;
    }
}
