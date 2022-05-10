package Algorithm.test06;

/**
 * author ye
 * createDate 2022/2/27  19:32
 */
public class solution110 {
    /*public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(isTrue(root.left) - isTrue(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    public int isTrue(TreeNode root){
        if (root == null) return 0;
        return Math.max(isTrue(root.left), isTrue(root.right)) + 1;
    }*/
    public boolean isBalanced(TreeNode root) {
        return isTure(root) != -1;
    }
    public int isTure(TreeNode root){
        if (root == null) return 0;
        int left = isTure(root.left);
        int right = isTure(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        //int leftHeight, rightHeight;
        //if ((leftHeight = isTure(root.left)) == -1 || (rightHeight = isTure(root.right)) == -1 || Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}
