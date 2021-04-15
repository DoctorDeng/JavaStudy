package datastructureAndalgorithm.practice;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

public class IntegerSqrtTest {

    @Test
    public void sqrt() {
        for (int i = 0; i < 100; i++) {
            int value = RandomUtils.nextInt();
            double expect = Math.sqrt(value);
            double actual = IntegerSqrt.sqrt(value);
            //System.out.println("expect:" + expect + " actual:" + actual);
            Assert.assertEquals(IntegerSqrt.round(expect, 6), actual, 0);
        }
    }
}