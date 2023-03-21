package datastructureAndalgorithm.practice.offer;

/**
 * 剑指 Offer 04. 二维数组中的查找.
 * <pre>
 * 在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，每一列都按照从上到下 非递减 的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例：现有矩阵 matrix 如下：
 *     [
 *       [1,   4,  7, 11, 15], x = 0
 *       [2,   5,  8, 12, 19], x = 1
 *       [3,   6,  9, 16, 22], x = 2
 *       [10, 13, 14, 17, 24], x = 3
 *       [18, 21, 23, 26, 30]  x = 4
 *     ]
 *   给定 target = 5，返回 true。
 *   给定 target = 20，返回 false。
 *
 * 限制：
 *   0 <= n <= 1000
 *   0 <= m <= 1000
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof">剑指 Offer 04. 二维数组中的查找</a>
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/21 19:41
 */
public class Offer04 {

	/**
	 * 思路：
	 *     [
	 *       [1,   4,  7, 11, 15], x = 0
	 *       [2,   5,  8, 12, 19], x = 1
	 *       [3,   6,  9, 16, 22], x = 2
	 *       [10, 13, 14, 17, 24], x = 3
	 *       [18, 21, 23, 26, 30]  x = 4
	 *     ]
	 *  暴力法遍历矩阵.
	 *  y = 0, 从 x = 0... n 方向扫描.
	 *  y = 1, 从 x = 0... n 方向扫描.
	 *  ...
	 *  逐行扫描直至找到符合条件的数.
	 *  该方法效率不高.
	 */
	public boolean findNumberIn2DArrayByLoop(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		if (target < matrix[0][0]) {
			return false;
		}
		int x = 0;
		int y = 0;
		int xMax = matrix.length - 1;
		int yMax = matrix[0].length - 1;
		while (y <= yMax) {
			for (int i = x; i <= xMax; i++) {
				int value = matrix[i][y];
				if (target == value) {
					return true;
				}
				if (target > value) {
					continue;
				}
				// 如果 target 比 x,y 小则表示不存在, 不用继续扫描.
				if (i == x) {
					return false;
				}
				break;
			}
			y++;
		}
		return false;
	}

	/**
	 * 思路:
	 *     [
	 *       [1,   4,  7, 11, 15], x = 0
	 *       [2,   5,  8, 12, 19], x = 1
	 *       [3,   6,  9, 16, 22], x = 2
	 *       [10, 13, 14, 17, 24], x = 3
	 *       [18, 21, 23, 26, 30]  x = 4
	 *     ]
	 *  将数组旋转 45° 将其转换为二叉树, 然后通过二叉树进行查找.
	 *  如上所示, 15(x=0,y=4)为二叉树根节点, 该节点左边的节点比该节点小, 该节点下边的节点比该节点大.
	 */
	public static boolean findNumberIn2DArrayByTreeSearch(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		if (target < matrix[0][0]) {
			return false;
		}
		int xMax = matrix.length - 1;
		int yMax = matrix[0].length - 1;

		int x = 0;
		int y = yMax;
		while (x <= xMax && y >= 0) {
			int value = matrix[x][y];
			if (target == value) {
				return true;
			}
			if (target > value) {
				x++;
			}
			else {
				y--;
			}
		}
		return false;
	}

}
