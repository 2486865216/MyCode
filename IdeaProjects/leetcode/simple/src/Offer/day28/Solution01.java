package Offer.day28;

import Offer.Utils.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * author ye
 * createDate 2022/4/14  21:07
 */
public class Solution01 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "[]";
        Queue<TreeNode> stack = new LinkedList<>();
        stack.offer(root);
        StringBuilder res = new StringBuilder("[");
        while (!stack.isEmpty()){
            TreeNode node = stack.poll();
            if (node != null){
                res.append(node.val + ",");
                stack.offer(node.left);
                stack.offer(node.right);
            }else {
                res.append("null,");
            }
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("[]".equals(data)) return null;
        String[] split = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        Queue<TreeNode> stack = new LinkedList<>();
        stack.offer(root);
        int i = 1;
        while (!stack.isEmpty()){
            TreeNode node = stack.poll();
            if (! split[i].equals("null")){
                node.left = new TreeNode(Integer.parseInt(split[i]));
                stack.offer(node.left);
            }
            i++;
            if (! split[i].equals("null")){
                node.right = new TreeNode(Integer.parseInt(split[i]));
                stack.offer(node.right);
            }
            i++;
        }
        return root;
    }
}
