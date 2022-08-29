package Algorithm.test08;

import Offer.Utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * author ye
 * createDate 2022/4/19  12:32
 */
public class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null){
            while (node != null){
                list.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.poll();
            node = node.right;
        }
        return list;
    }
}
