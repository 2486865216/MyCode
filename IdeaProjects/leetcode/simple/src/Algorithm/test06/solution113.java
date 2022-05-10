package Algorithm.test06;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/3/2  19:29
 */
public class solution113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        List<Integer> listSum = new ArrayList<>();
        paths(root, targetSum, list, listSum);
        return list;
    }
    public void paths(TreeNode root, int targetSum, List<List<Integer>> list, List<Integer> listSum){
        if (root == null) return;
        listSum.add(root.val);
        int size = listSum.size();
        if (root.left == null && root.right == null && root.val == targetSum){
                list.add(new ArrayList<>(listSum));
        }
        paths(root.left, targetSum - root.val, list, listSum);
        paths(root.right, targetSum - root.val, list, listSum);
        listSum.remove(listSum.size() - 1);
    }
}
