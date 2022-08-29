package Algorithm.test05;

/**
 * author ye
 * createDate 2022/2/22  19:41
 */
public class solution100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (q == null && p == null) return true;
        if (p == null && q != null || p != null && q == null) return false;
        if (p.val != q.val){
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    public boolean isSameNode(TreeNode p, TreeNode q){
        if (q == null && p == null) return true;
        if (p.val != q.val || p == null && q != null || p != null && q == null){
            return false;
        }
        return isSameNode(p.left, q.left) && isSameNode(p.right, q.right);
    }
}
