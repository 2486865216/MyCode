package Algorithm.test05;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/19  20:09
 */
public class solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        middle(root, list);
        return list;
    }
    public void middle(TreeNode root, List<Integer> list){
        if (root == null) return;
        list.add(root.val);
        middle(root.left, list);
        middle(root.right, list);
    }
}
