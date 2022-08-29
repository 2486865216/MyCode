package Offer.day15;

import Offer.Utils.TreeNode;

/**
 * author ye
 * createDate 2022/4/1  19:20
 */
public class Solution03 {
    int count = 0,res = 0;
    public int kthLargest(TreeNode root, int k) {
        dfs(root, k);
        return res;
    }

    public void dfs(TreeNode root, int k){
        if (root == null) return;
        dfs(root.right, k);
        if (++count == k){
            res = root.val;
            return;
        }
        dfs(root.left, k);
    }
}
