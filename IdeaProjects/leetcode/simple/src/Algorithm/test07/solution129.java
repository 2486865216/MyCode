package Algorithm.test07;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/3/14  19:00
 */
public class solution129 {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    public int dfs(TreeNode root, int sum){
        if (root == null) return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) return sum;
        return dfs(root.left, sum) + dfs(root.right, sum);
    }

    @Test
    public void test() {
        sumNumbers(new TreeNode(10));
    }
}
