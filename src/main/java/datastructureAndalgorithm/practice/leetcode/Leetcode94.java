package datastructureAndalgorithm.practice.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Leetcode 94. 二叉树的中序遍历:
 * <pre>
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历.
 *
 * 提示：
 *   树中节点数目在范围 [0, 100] 内
 *   -100 <= Node.val <= 100
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/">94. 二叉树的中序遍历</a>
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/17 19:31
 */
public class Leetcode94 {


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

	/**
	 * 思路：使用栈入栈、出栈来模拟方法的递归调用.
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		if (root == null) {
			return Collections.emptyList();
		}
		List<Integer> values = new ArrayList<>(100);
		Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode temp = stack.pop();
			if (temp != null) {
				// 中序遍历, 右边的节点最后, 所以要最先入栈(先进后出).
				stack.push(temp.right);
				stack.push(temp);
				stack.push(temp.left);
			}
			else {
				// temp 为空表示到达叶子节点, 此时将叶子节点放入值放入结果集中.
				if (!stack.isEmpty()) {
					values.add(stack.pop().val);
				}
			}
		}
		return values;
	}

	public List<Integer> inorderTraversal2(TreeNode root) {
		if (root == null) {
			return Collections.emptyList();
		}
		List<Integer> values = new ArrayList<>(100);
		Deque<TreeNode> stack = new LinkedList<>();
		while (!stack.isEmpty() || root != null) {
			// 不断遍历左子节点, 直到叶子节点.
			if (root != null) {
				stack.push(root);
				root = root.left;
			}
			else {
				// 到达叶子节点, 将值添加到结果集中.
				TreeNode node = stack.pop();
				values.add(node.val);
				// 然后遍历右子树.
				root = node.right;
			}
		}
		return values;
	}

	public List<Integer> inorderTraversal(TreeNode root, List<Integer> values) {
		if (root == null) {
			return values;
		}
		inorderTraversal(root.left, values);
		values.add(root.val);
		inorderTraversal(root.right, values);
		return values;
	}

}
