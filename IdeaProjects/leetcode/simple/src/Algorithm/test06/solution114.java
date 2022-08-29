package Algorithm.test06;

/**
 * author ye
 * createDate 2022/3/2  20:02
 */
public class solution114 {
    //List<Integer> list = new ArrayList<>();
    //public void flatten(TreeNode root) {
    //    if (root == null) return;
    //    forEachNode(root);
    //    for (int i = 1; i < list.size(); i++) {
    //        root.right = new TreeNode(list.get(i));
    //        root = root.left;
    //    }
    //}
    //public void forEachNode(TreeNode root){
    //    if (root == null) return;
    //    list.add(root.val);
    //    forEachNode(root.left);
    //    forEachNode(root.right);
    //}
    public void flatten(TreeNode root) {
        TreeNode current = root;
        while (current != null){
            if (current.left != null){
                TreeNode next = current.left;
                TreeNode previous = next;
                while (previous.right != null){
                    previous = previous.right;
                }
                previous.right = current.right;
                current.right = next;
                current.left = null;
            }
            current = current.right;
        }
    }
}
