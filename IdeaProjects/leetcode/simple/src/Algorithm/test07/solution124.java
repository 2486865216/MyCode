package Algorithm.test07;

/**
 * author ye
 * createDate 2022/3/8  20:00
 */
public class solution124 {
    int num = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        def(root);
        return num;
    }
    public int def(TreeNode root){
        if (root == null) return 0;
        int left = Math.max(def(root.left), 0);
        int right = Math.max(def(root.right), 0);
        int current = root.val;
        num = Math.max(current + left + right, num);
        return current + Math.max(left, right);
    }
}
