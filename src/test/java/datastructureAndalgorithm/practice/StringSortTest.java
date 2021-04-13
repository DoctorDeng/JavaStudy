package datastructureAndalgorithm.practice;

import org.junit.Assert;
import org.junit.Test;


public class StringSortTest {

    @Test
    public void lowercaseBeforeUppercase() {
        String test = "ab2323CA";
        Assert.assertEquals(test, StringSort.lowercaseBeforeUppercase(test));
        test = "aAb2323CA";
        Assert.assertEquals("abA2323CA", StringSort.lowercaseBeforeUppercase(test));
        test = "AB23A23b";
        Assert.assertEquals("bB23A23A", StringSort.lowercaseBeforeUppercase(test));
    }

}