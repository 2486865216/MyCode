package Algorithm.test06;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * author ye
 * createDate 2022/2/26  20:00
 */
public class solution107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null){
            return null;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()){
            int size = nodeQueue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = nodeQueue.poll();
                list.add(current.val);
                if (current.left != null){
                    nodeQueue.offer(current.left);
                }
                if (current.right != null){
                    nodeQueue.offer(current.right);
                }
            }
            lists.add(0,list);
        }
        //List<List<Integer>> lists2 = new ArrayList<>();
        //for (int i = lists.size() - 1; i >= 0; i--) {
        //    lists2.add(lists.get(i));
        //}
        //return lists2;
        return lists;
    }
}
