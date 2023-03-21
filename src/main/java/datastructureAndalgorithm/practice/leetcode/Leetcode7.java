package datastructureAndalgorithm.practice.leetcode;

/**
 * Leetcode 7. 整数反转.
 * <pre>
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * 示例 1：
 *     输入：x = 123
 *     输出：321
 * 示例 2：
 *     输入：x = -123
 *     输出：-321
 *  示例 3:
 *     输入：x = 120
 *     输出：21
 *  示例 4:
 *     输入：x = 0
 *     输出：0
 *
 *  提示: -2^31(-2147483648) <= x <= 2^31 - 1(2147483647)
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/reverse-integer/description/">7. 整数反转</a>
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/21 19:04
 */
public class Leetcode7 {

	/**
	 * 思路：
	 *  123 翻转后为 321 = 3 * 10 * 10 + 2 * 10 + 1, 其中 1 = 321 % 10、2 = 32 % 10、3 = 3 % 10
	 *  因此将输入的数(x)与 10 求余数，然后将该数值除以 10 不断循环可依次求得个位数->十位数->百位数..... 的值.
	 *  然后将这些值不断的乘以 10 即可得到翻转的数.
	 */
	public static int reverse(int x) {
		if (x == 0) {
			return 0;
		}
		int value = 0;
		while (x != 0) {
			int temp = x % 10;
			/**
			 * 溢出判定:
			 *   int 类型值为 2^31(-2147483648) <= x <= 2^31 - 1(2147483647)
			 *   如果值在最后一步大于 214748364 或小于 -214748364 则值 * 10 后会溢出.
			 *   如果值在最后一步等于  214748364 或 -214748364, 且原值最高位数大于 7 或小于 -8 则会溢出.
			 */
			if (value > 214748364 || value == 214748364 && temp > 7) {
				return 0;
			}
			if (value < -214748364 || value == -214748364 && temp < -8) {
				return 0;
			}
			x = x / 10;
			value = value * 10 + temp;
		}
		return value;
	}


}
