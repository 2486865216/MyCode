package Algorithm.test06;

/**
 * author ye
 * createDate 2022/2/24  20:04
 */
public class solution104 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
