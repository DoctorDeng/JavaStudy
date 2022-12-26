package datastructureAndalgorithm.practice.leetcode;

/**
 * Leetcode 5 题:
 * <pre>
 * 给你一个字符串 s，找到 s 中最长的回文子串.
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/longest-palindromic-substring">Leetcode 5: 最长回文子串</a>
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/12/25 19:52
 */
public class Leetcode5 {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int length = s.length();
        if (length == 1) {
            return s;
        }
        int left = 0;
        int right = 0;
        for (int i = 0, len = length - 1; i < len; i++) {
            // 步进, 为 1 或者 2. 用于测试偶数和奇数长度的回文串.
            int step = 1;
            int max = longestPalindrome(s, i, i + 1);
            if (i + 2 < length) {
                // 求长度为奇数的回文串长度.
                int oddLen = longestPalindrome(s, i, i + 2);
                if (oddLen > max && oddLen > right - left) {
                    max = oddLen;
                    step = 2;
                }
            }
            if (max > right - left) {
                left = i - (max - step)/2;
                right = i + (max - step)/2 + step;
            }
        }
        return s.substring(left, right + 1);
    }

    /**
     * 从指定字符串 s 的 left 与 right 位置开始进行扩散, 求得回文串长度.
     */
    private static int longestPalindrome(String s, int left, int right) {
        int length = s.length();
        while (left >= 0 && right < length){
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                continue;
            }
            break;
        }
        return right - left - 2;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aaaaa"));
    }

}
