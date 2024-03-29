package datastructureAndalgorithm.practice.offer;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 03. 数组中重复的数字.
 * <pre>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次.
 * 请找出数组中任意一个重复的数字.
 * 示例 1：
 *     输入：[2, 3, 1, 0, 2, 5, 3]
 *     输出：2 或 3
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof">剑指 Offer 03. 数组中重复的数字</a>
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/7 19:21
 */
public class Offer03 {

	/**
	 * 思路：创建一个集合, 遍历数组, 判断数据是否在集合中没有则添加至集合, 否则则表示存在重复数据, 直接返回重复数据.
	 * 时间复杂度：O(n)
	 * 空间复杂度：O(n)
	 * 利用 Set 判重比较通用.
	 */
	public int findRepeatNumber(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		Set<Integer> sets = new HashSet<>(nums.length);
		for (int num : nums) {
			if (sets.contains(num)) {
				return num;
			} else {
				sets.add(num);
			}
		}
		return -1;
	}

	/**
	 * 思路：在一个长度为 n 的数组 nums 里的所有数字都在 0 ~ n-1 的范围内, 说明数组元素的 索引 和值是一对多的关系.
	 * 时间复杂度：O(n)
	 * 空间复杂度：O(1)
	 */
	public int findRepeatNumberInPlace(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int i = 0;
		int length = nums.length;
		while (i < length) {
			int num = nums[i];
			if (num == i) {
				i++;
				continue;
			}
			int idxNum = nums[num];
			if (num == idxNum) {
				return num;
			}
			else {
				nums[num] = num;
				nums[i] = idxNum;
			}
		}
		return -1;
	}

}
