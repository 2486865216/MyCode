package Algorithm.test05;

/**
 * author ye
 * createDate 2022/2/21  20:07
 */
public class solution98 {
    long prev = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        return isTrue(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isTrue(TreeNode root,long left,long right) {
        if (root == null) return true;
        if (root.val <= left) return false;
        if (root.val >= right) return false;
        return isTrue(root.left, left, root.val) && isTrue(root.right, root.val, right);
    }
    public boolean isTrue(TreeNode root){
        if (root ==null) return true;
        boolean left = isTrue(root.left);
        if (root.val <= prev) return false;
        prev = root.val;
        boolean right = isTrue(root.right);
        return left && right;
    }
}
