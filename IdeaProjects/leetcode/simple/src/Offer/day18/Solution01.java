package Offer.day18;

import Offer.Utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * author ye
 * createDate 2022/4/4  18:37
 */
public class Solution01 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> stack = new LinkedList<>();
        stack.offer(root);
        int depth = 0;
        while (!stack.isEmpty()){
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = stack.poll();
                if (cur.left != null){
                    stack.offer(cur.left);
                }
                if (cur.right != null){
                    stack.offer(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
