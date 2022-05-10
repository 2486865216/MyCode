package Algorithm.test08;

import Offer.Utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * author ye
 * createDate 2022/4/19  18:28
 */
public class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) return list;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == pre){
                list.add(root.val);
                pre = root;
                root = null;
            }else {
                stack.push(root);
                root = root.right;
            }

        }
        return list;
    }
}
