package Offer.Another;

import Offer.Utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * author ye
 * createDate 2022/5/5  19:50
 */
public class solution257 {
    public List<String> binaryTreePaths(TreeNode root) {
        //List<String> path = new ArrayList<>();
        //dfs(root, "", path);
        //return path;
        List<String> paths = new ArrayList<>();
        if (root == null) return paths;

        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();

        nodes.offer(root);
        pathQueue.offer(String.valueOf(root.val));

        while (!nodes.isEmpty()) {
            TreeNode current = nodes.poll();
            String path = pathQueue.poll();

            if (current.left == null && current.right == null) {
                paths.add(path);
            } else {
                if (current.left != null) {
                    nodes.offer(current.left);
                    pathQueue.offer(new StringBuilder(path).append("->").append(current.left.val).toString());
                }
                if (current.right != null) {
                    nodes.offer(current.right);
                    pathQueue.offer(new StringBuilder(path).append("->").append(current.right.val).toString());
                }
            }
        }
        return paths;
    }

    public void dfs(TreeNode root, String path, List<String> pathList) {
        if (root != null) {
            StringBuilder stringBuilder = new StringBuilder(path);
            stringBuilder.append(root.val);
            if (root.left == null && root.right == null) {
                pathList.add(stringBuilder.toString());
            } else {
                stringBuilder.append("->");
                dfs(root.left, stringBuilder.toString(), pathList);
                dfs(root.right, stringBuilder.toString(), pathList);
            }
        }
    }
}
