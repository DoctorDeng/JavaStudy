package datastructureAndalgorithm.practice.leetcode;

import util.PrintUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode 1 题:
 * <pre>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标.
 *
 * 你可以假设每种输入只会对应一个答案. 但是，数组中同一个元素在答案里不能重复出现.
 *
 * 你可以按任意顺序返回答案.
 * </pre>
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 说明见 <a href=https://leetcode.cn/problems/two-sum>Leetcode 1: 两数之和</a>
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/12/24 20:05
 */
public class Leetcode1 {

    /**
     * 通过双重 for 循环求解, 思路简单但效率低.
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] result = new int[2];
        int len = nums.length;
        boolean exists = false;
        for (int i = 0, le = len - 1; i < le; i++) {
            int inum = nums[i];
            for (int j = i + 1; j < len; j++) {
                int jnum = nums[j];
                int count = inum + jnum;
                if (target == count) {
                    result[0] = i;
                    result[1] = j;
                    exists = true;
                }
            }
        }
        if (exists) {
            return result;
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Map<Integer, Integer> data = new HashMap<>(nums.length);
        int[] result = new int[2];
        for (int i = 0, len = nums.length; i < len; i++) {
            int val = nums[i];
            int expect = target - val;
            if (data.containsKey(expect)) {
                result[0] = i;
                result[1] = data.get(expect);
                return result;
            }
            data.put(val, i);
        }
        return null;
    }

    public static void main(String[] args) {
        PrintUtil.print(twoSum2(new int[]{3,2,4}, 6));
    }

}
