package datastructureAndalgorithm.practice;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串排序练习.
 * @author <a href="https://doctordeng.github.io">DoctorDeng</a>
 * @date 2021/4/13 15:04
 * @since 1.0.0
 */
public class StringSort {
    // 将字符串中的小写字符排在大小字母前面
    public static String lowercaseBeforeUppercase(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        // 原字符串拷贝
        char[] result = new char[str.length()];
        str.getChars(0, str.length(), result, 0);

        for (int upperCursor  = 0, lowerCursor = result.length - 1,  len = result.length; upperCursor < len; upperCursor++) {
            char upper = result[upperCursor];
            if (Character.isUpperCase(upper)) {
                for (; lowerCursor > upperCursor; lowerCursor--) {
                    char lower = result[lowerCursor];
                    if (Character.isLowerCase(lower)) {
                        result[lowerCursor] = upper;
                        result[upperCursor] = lower;
                        break;
                    }
                }
            }
        }
        return String.copyValueOf(result);
    }
}
