package datastructureAndalgorithm.practice.leetcode;

/**
 * leetcode 104 题：求二叉树的最大深度.
 *
 * @author <a href="https://doctordeng.github.io">DoctorDeng</a>
 * @date 2021/5/21 20:38
 * @since 1.0.0
 */
public class Leetcode104 {

}
class SimpleRecursionSolution {
    // 通过递归求解, 更加简洁
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
class RecursionSolution {
    // 通过递归求解
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 1;
        int leftDepth  = root.left  == null ? depth : maxDepth(root.left, depth);
        int rightDepth = root.right == null ? depth : maxDepth(root.right, depth);
        return Math.max(leftDepth, rightDepth);
    }
    private int maxDepth(TreeNode node, int depth) {
        depth++;
        if (node.left == null && node.right == null) {
            return depth;
        }
        int leftDepth  = node.left  == null ? depth : maxDepth(node.left, depth);
        int rightDepth = node.right == null ? depth : maxDepth(node.right, depth);
        return Math.max(leftDepth, rightDepth);
    }
}
// TODO: 通过队列求解
class QueueSolution {

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {
    }
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}