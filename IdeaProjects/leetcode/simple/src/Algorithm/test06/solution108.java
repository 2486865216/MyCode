package Algorithm.test06;

/**
 * author ye
 * createDate 2022/2/26  20:24
 */
public class solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return BST(nums, 0 , nums.length - 1);
    }

    private TreeNode BST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = BST(nums, left, mid - 1);
        root.left = BST(nums, mid + 1, right);
        return root;
    }
}
