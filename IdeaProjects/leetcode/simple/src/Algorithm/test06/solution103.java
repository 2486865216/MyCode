package Algorithm.test06;

import java.util.*;

/**
 * author ye
 * createDate 2022/2/24  19:40
 */
public class solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;

        boolean isLeftToRight = true;
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            Deque<Integer> deque = new ArrayDeque<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = nodeQueue.poll();
                if (isLeftToRight){
                    deque.offerLast(cur.val);
                }else {
                    deque.offerFirst(cur.val);
                }
                if (cur.left != null){
                    nodeQueue.offer(cur.left);
                }
                if (cur.right != null){
                    nodeQueue.offer(cur.right);
                }
            }
            lists.add(new ArrayList<>(deque));
            isLeftToRight = !isLeftToRight;
        }
        return lists;
    }
}
