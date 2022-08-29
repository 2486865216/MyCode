package Algorithm.test05;

import java.util.LinkedList;
import java.util.List;

/**
 * author ye
 * createDate 2022/2/20  19:27
 */
public class solution95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0){
            return new LinkedList<>();
        }
        return generation(1, n);
    }

    private List<TreeNode> generation(int start, int end) {
        List<TreeNode> list = new LinkedList();
        if (start > end) {
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generation(start, i - 1);
            List<TreeNode> rightTrees = generation(i + 1, end);
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode currentTree = new TreeNode(i);
                    currentTree.left = leftTree;
                    currentTree.right = rightTree;
                    list.add(currentTree);
                }
            }
        }
        return list;
    }
}
