package Offer.day06;

import Offer.Utils.TreeNode;

import java.util.*;

/**
 * author ye
 * createDate 2022/3/23  18:31
 */
public class Solution03 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> queueNode = new LinkedList<>();
        List<List<Integer>> lists = new ArrayList<>();
        queueNode.offer(root);
        boolean leftToRight = true;
        while (!queueNode.isEmpty()){
            int size = queueNode.size();
            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queueNode.poll();
                if (leftToRight){
                    deque.offerLast(current.val);
                }else {
                    deque.offerFirst(current.val);
                }
                if (current.left != null){
                    queueNode.offer(current.left);
                }
                if (current.right != null){
                    queueNode.offer(current.right);
                }
            }
            lists.add(new ArrayList<>(deque));
            leftToRight = !leftToRight;
        }
        return lists;
    }
}
