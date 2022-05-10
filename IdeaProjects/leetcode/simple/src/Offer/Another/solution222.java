package Offer.Another;

import Offer.Utils.TreeNode;

/**
 * author ye
 * createDate 2022/4/28  20:31
 */
public class solution222 {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int level = 0;
        TreeNode node = root;
        while (node.left != null){
            level++;
            node = node.left;
        }
        int low = 1 << level, height = (1 << (level + 1)) - 1;
        while (low < height){
            int mid = (height - low + 1) / 2 + low;
            if (exist(root, level, mid)){
                low = mid;
            }else {
                height = mid - 1;
            }
        }
        return low;
    }

    private boolean exist(TreeNode root, int level, int mid) {
        int bit = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bit > 0){
            if ((bit & mid) == 0){
                node = node.left;
            }else {
                node = node.right;
            }
            bit >>= 1;
        }
        return node != null;
    }
}
