package Offer.day15;

import Offer.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/4/1  18:30
 */
public class Solution01 {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> list1 = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) return null;
        dfs(root ,target);
        return list;
    }

    public void dfs(TreeNode root, int target){
        if (root == null) return;
        target -= root.val;
        list1.add(root.val);
        if (target == 0 && root .left == null && root.right == null){
            list.add(new ArrayList<>(list1));
            return;
        }
        dfs(root.left, target);
        dfs(root.right, target);

        list1.remove(list1.size() - 1);
    }
}
