package datastructureAndalgorithm.practice.offer;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I.
 * <pre>
 * 统计一个数字在排序数组中出现的次数.
 * 示例 1：
 *     输入: nums = [5,7,7,8,8,10], target = 8
 *     输出: 2
 * 示例 2：
 *     输入: nums = [5,7,7,8,8,10], target = 6
 *     输出: 0
 * 提示：
 *     0 <= nums.length <= 105
 *     -109 <= nums[i] <= 109
 *     nums 是一个非递减数组
 *     -109 <= target <= 109
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof">剑指 Offer 53 - I. 在排序数组中查找数字 I</a>
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/7 20:13
 */
public class Offer53_1 {

	/**
	 * 思路：通过二分法查找查找数组中与 target 相等的第一个值, 然后以该值向数组前后扫描, 得到与 target 相等的元素个数.
	 */
	public static int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int num = 0;
		int start = 0;
		int end = nums.length - 1;
		while (start <= end) {
			int mid = (end + start) >> 1;
			int midNum = nums[mid];

			if (midNum == target) {
				num++;
				for (int i = mid - 1; i >= 0; i--) {
					if (nums[i] == target) {
						num++;
					}
					else {
						break;
					}
				}
				for (int i = mid + 1, len = nums.length; i < len; i++) {
					if (nums[i] == target) {
						num++;
					}
					else {
						break;
					}
				}
				return num;
			}
			if (midNum > target) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		return num;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1};
		int target = 1;
		System.out.println(search(nums, target));
	}

}
