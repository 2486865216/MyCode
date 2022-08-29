package Offer.Another;

import Offer.Utils.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * author ye
 * createDate 2022/5/8  18:35
 */
public class solution2457 {
    Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
