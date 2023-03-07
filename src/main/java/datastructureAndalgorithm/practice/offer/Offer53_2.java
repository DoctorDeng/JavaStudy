package datastructureAndalgorithm.practice.offer;

/**
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字.
 * <pre>
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内.
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字.
 *
 * 示例 1：
 *     输入: [0,1,3]
 *     输出: 2
 * 示例 2：
 *     输入: [0,1,2,3,4,5,6,7,9]
 *     输出: 8
 * 限制：
 *     1 <= 数组长度 <= 10000
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/que-shi-de-shu-zi-lcof">剑指 Offer 53 - II. 0～n-1中缺失的数字</a>
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/7 20:45
 */
public class Offer53_2 {

	/**
	 * 思路：0~ n -1 范围比对给定数组, 指定下标不存在则确实该数字.
	 */
	public int missingNumber(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if (nums[i] != i) {
				return i;
			}
		}
		return n;
	}
}
