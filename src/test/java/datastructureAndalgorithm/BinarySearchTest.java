package datastructureAndalgorithm;

import datastructureAndalgorithm.sort.SortUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BinarySearchTest {

    @Test
    public void simpleBinarySearch() {
        int[] test = new int[]{0,2,3,4,5};
        Assert.assertEquals(0, BinarySearch.simpleBinarySearch(test, 0));
        Assert.assertEquals(1, BinarySearch.simpleBinarySearch(test, 2));
        Assert.assertEquals(2, BinarySearch.simpleBinarySearch(test, 3));
        Assert.assertEquals(3, BinarySearch.simpleBinarySearch(test, 4));
        Assert.assertEquals(4, BinarySearch.simpleBinarySearch(test, 5));
        Assert.assertEquals(-1, BinarySearch.simpleBinarySearch(test, -2));
    }

    @Test
    public void findFirstEqual() {
        int[] test = new int[]{0,2,2,2,5};
        Assert.assertEquals(1, BinarySearch.findFirstEqual(test, 2));
        test = new int[]{2,2,2,2,2};
        Assert.assertEquals(0, BinarySearch.findFirstEqual(test, 2));
        test = new int[]{0,0,0,2,2};
        Assert.assertEquals(3, BinarySearch.findFirstEqual(test, 2));

        for (int i = 0; i < 100; i++) {
            test = SortUtils.getTestData(0, 5, 20);
            int value = test[5];
            Arrays.sort(test);
            Assert.assertEquals(ArrayUtils.indexOf(test, value), BinarySearch.findFirstEqual(test, value));
        }
    }

    @Test
    public void findFirstEqual2() {
        int[] test = new int[]{0,2,2,2,5};
        Assert.assertEquals(1, BinarySearch.findFirstEqual2(test, 2));
        test = new int[]{2,2,2,2,2};
        Assert.assertEquals(0, BinarySearch.findFirstEqual2(test, 2));
        test = new int[]{0,0,0,2,2};
        Assert.assertEquals(3, BinarySearch.findFirstEqual2(test, 2));

        for (int i = 0; i < 100; i++) {
            test = SortUtils.getTestData(0, 5, 20);
            int value = test[5];
            Arrays.sort(test);
            Assert.assertEquals(ArrayUtils.indexOf(test, value), BinarySearch.findFirstEqual2(test, value));
        }
    }
}