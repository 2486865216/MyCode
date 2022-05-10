package Offer.Another;

import Offer.Utils.TreeNode;

/**
 * author ye
 * createDate 2022/4/27  20:39
 */
public class solution236 {
    TreeNode res = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p ,q);
        return res;
    }
    public boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if (root == null) return false;
        boolean left = dfs(root.left, p, q);
        boolean right = dfs(root.right, p, q);
        if ((left  && right) || (root.val == p.val || root.val == q.val)){
            res = root;
        }
        return left || right || (root.val == p.val || root.val == q.val);
    }
}
