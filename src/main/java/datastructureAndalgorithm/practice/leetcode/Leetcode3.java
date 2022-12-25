package datastructureAndalgorithm.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Leetcode 3 题:
 * <pre>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度.
 * </pre>
 * <p>
 * 说明见 <a href=https://leetcode.cn/problems/longest-substring-without-repeating-characters>Leetcode 3: 无重复字符的最长子串</a>
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/12/25 18:42
 */
public class Leetcode3 {

    /**
     * 最开始的版本, 每次发现重复字符串, 都将窗口左边的字符串移除了.
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> datas = new HashMap<>(s.length());
        int max = 0;
        int left = 0;
        for (int i = 0, len = s.length(); i < len; i++) {
            char chr = s.charAt(i);
            if (datas.containsKey(chr)) {
                int length = i - left;
                max = Math.max(length, max);
                int repeatIdx = datas.get(chr);
                for (int j = left; j <= repeatIdx; j++) {
                    datas.remove(s.charAt(j));
                }
                left = repeatIdx + 1;
            }
            else if (i == len - 1) {
                max = Math.max(max,  i - left + 1);
            }
            datas.put(chr, i);
        }
        return max;
    }

    /**
     * 思想上基于滑动窗口, 每次发现重复的字符串都将窗口右移.
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> chars = new HashMap<>(s.length());
        int max = 0;
        int left = 0;
        for (int i = 0, len = s.length(); i < len; i++) {
            char chr = s.charAt(i);
            if (chars.containsKey(chr)) {
                // 由于窗口右移, 没有丢弃 chars 中窗口左侧的多余的字符串, 所以要比较大小.
                left =  Math.max(left , chars.get(chr) + 1);
            }
            // 每次都更新最大长度, 就没有第一版中需要额外在遍历结束时计算一下长度.
            max = Math.max(max,  i - left + 1);
            chars.put(chr, i);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
    }

}
