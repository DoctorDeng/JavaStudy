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
            int len1 = longestPalindrome(s, i, i + 1);
            if (i + 2 < length) {
                int len2 = longestPalindrome(s, i, i + 2);
                if (len2 > len1 && len2 > right - left) {
                    left = i - (len2 - 2)/2;
                    right = i + (len2 - 2)/2 + 2;
                    continue;
                }
            }
            if (len1 > right - left) {
                left = i - (len1 - 1)/2;
                right = i + (len1 - 1)/2 + 1;
            }
        }
        return s.substring(left, right + 1);
    }

    private static int longestPalindrome(String s, int left, int right) {
        int length = s.length();
        int loopNum = -1;
        int base = right - left;
        while (left >= 0 && right < length){
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                loopNum++;
                continue;
            }
            break;
        }
        return base + loopNum * 2;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abc"));
    }

}
