package datastructureAndalgorithm.practice.offer;

/**
 * 剑指 Offer 11. 旋转数组的最小数字.
 * <pre>
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *
 * 给你一个可能存在重复元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。
 * 请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
 *
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 示例 1：
 *     输入：numbers = [3,4,5,1,2]
 *     输出：1
 *
 * 示例 2：
 *     输入：numbers = [2,2,2,0,1]
 *     输出：0
 *
 * 提示
 *     n == numbers.length
 *     1 <= n <= 5000
 *     -5000 <= numbers[i] <= 5000
 *     numbers 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/">剑指 Offer 11. 旋转数组的最小数字</a>
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/22 20:46
 */
public class Offer11 {

	/**
	 * [1,2,2,2,3,3,3,3,3,3,3,3,1,1,1]
	 * [2,2,2,3,3,3,3,3,3,3,3,0,0,1]
	 * [3,3,3,3,3,3,3]
	 * [3,4,5,1,2]
	 */
	public static int minArray(int[] numbers) {
		if (numbers == null || numbers.length == 0) {
			throw new IllegalArgumentException("numbers must not be null or empty");
		}
		/**
		 * <pre>
		 * 翻转前数组为, 其中
		 *   point 为翻转点.
		 *   --> 表示数组朝该方向升序
		 *
		 * [start --->  point, point + 1 ---->  end]
		 *
		 * 翻转后数组被分为如下两部分 A 和 B.
		 *        A 部分                B 部分
		 *        ↓                    ↓
		 * [point + 1 ---->  end]  [start --->  point]
		 *
		 * 现在要求得翻转后的 start 位置的最小值, 可以通过二分法逐渐查找出 start 位置.
		 *
		 * 如果 Mid 大于 Right 则表示 Mid 在 A 部分, 示意图如下:
		 *    Left                      Mid                         Right
		 *     ↓                       ↓                            ↓
		 *  [point + 1, point + 2, point + 3 ---->  end]  [start, start + 1, start + 2 --->  point]
		 *
		 *  如果 Mid 小于 Right 则表示 Mid 在 B 部分, 示意图如下:
		 *                          Left                          Mid          Right
		 *                           ↓                           ↓            ↓
		 *  [point + 1, point + 2, point + 3 ---->  end]  [start, start + 1, start + 2 --->  point]
		 *
		 *  如果不属于上面的情况，则表示可能存在重复值, 需要遍历 left ~ right 才能够获得真正的 start 位置的值.
		 * </pre>
		 */
		int start = 0;
		int end = numbers.length - 1;
		while (start < end) {
			int mid = start + ((end - start) >> 1);
			int midV = numbers[mid];
			int endV =  numbers[end];
			if (midV > endV) {
				start = mid + 1;
			}
			else if (midV < endV) {
				end = mid;
			}
			else {
				for (int i = start + 1; i < end; i++) {
					if (numbers[i] < numbers[start]) {
						start = i;
					}
				}
				return numbers[start];
			}
		}
		return numbers[start];
	}

	public static void main(String[] args) {
		int[] array = new int[]{2,2,2,0,1};
//		System.out.println(3 + ((4 - 3) >> 1));
//		System.out.println((3 + 4)/2);
		System.out.println(minArray(array));

	}

}
