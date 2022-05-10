package Algorithm.test05;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/22  19:25
 */
public class solution99 {
    TreeNode x = null;
    TreeNode y = null;
    TreeNode pre = null;
    public void recoverTree(TreeNode root) {
        //Deque<TreeNode> stack = new ArrayDeque<>();
        //TreeNode errorX = null;
        //TreeNode errorY = null;
        //TreeNode previousNode = null;
        //
        //while (root != null || !stack.isEmpty()){
        //    while (root != null){
        //        stack.push(root);
        //        root = root.left;
        //    }
        //    root = stack.pop();
        //    if (previousNode != null && root.val < previousNode.val){
        //        errorY = root;
        //        if (errorX == null){
        //            errorX = previousNode;
        //        }else {
        //            break;
        //        }
        //    }
        //    previousNode = root;
        //    root = root.right;
        //}
        //int temp = errorX.val;
        //errorX.val = errorY.val;
        //errorY.val = temp;
        test(root);
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }
    public void test(TreeNode root){
        if (root == null) return;
        test(root.left);
        if (pre != null && root.val < pre.val){
            y = root;
            if (x == null){
                x = pre;
            }else {
                return;
            }
        }
        pre = root;
        test(root.right);
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode root2 = new TreeNode(3);
        TreeNode root3 = new TreeNode(2);
        root.left = root2;
        root2.right = root3;
        recoverTree(root);
    }
}
