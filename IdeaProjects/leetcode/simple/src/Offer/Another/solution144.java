package Offer.Another;

import Offer.Utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * author ye
 * createDate 2022/5/9  13:38
 */
public class solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                list.add(root.val);
                stack.push(root);
                root = root .left;
            }
            root = stack.pop();
            root = root.right;
        }
        return list;
    }
}
