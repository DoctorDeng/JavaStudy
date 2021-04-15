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
    public void indexOf() {
        int[] test = new int[]{0,2,2,2,5};
        Assert.assertEquals(1, BinarySearch.indexOf(test, 2));
        test = new int[]{2,2,2,2,2};
        Assert.assertEquals(0, BinarySearch.indexOf(test, 2));
        test = new int[]{0,0,0,2,2};
        Assert.assertEquals(3, BinarySearch.indexOf(test, 2));

        for (int i = 0; i < 100; i++) {
            test = SortUtils.getTestData(0, 5, 20);
            int value = test[5];
            Arrays.sort(test);
            Assert.assertEquals(ArrayUtils.indexOf(test, value), BinarySearch.indexOf(test, value));
            Assert.assertEquals(-1, BinarySearch.lastIndexOf(test, 10));
        }
    }

    @Test
    public void indexOf2() {
        int[] test = new int[]{0,2,2,2,5};
        Assert.assertEquals(1, BinarySearch.indexOf2(test, 2));
        test = new int[]{2,2,2,2,2};
        Assert.assertEquals(0, BinarySearch.indexOf2(test, 2));
        test = new int[]{0,0,0,2,2};
        Assert.assertEquals(3, BinarySearch.indexOf2(test, 2));

        for (int i = 0; i < 100; i++) {
            test = SortUtils.getTestData(0, 5, 20);
            int value = test[5];
            Arrays.sort(test);
            Assert.assertEquals(ArrayUtils.indexOf(test, value), BinarySearch.indexOf2(test, value));
            Assert.assertEquals(-1, BinarySearch.lastIndexOf(test, 10));
        }
    }

    @Test
    public void lastIndexOf() {
        int[] test = new int[]{0,2,2,2,5};
        Assert.assertEquals(3, BinarySearch.lastIndexOf(test, 2));
        test = new int[]{2,2,2,2,2};
        Assert.assertEquals(4, BinarySearch.lastIndexOf(test, 2));
        test = new int[]{2,2,3,3,3};
        Assert.assertEquals(1, BinarySearch.lastIndexOf(test, 2));

        for (int i = 0; i < 100; i++) {
            test = SortUtils.getTestData(0, 5, 20);
            int value = test[5];
            Arrays.sort(test);
            Assert.assertEquals(ArrayUtils.lastIndexOf(test, value), BinarySearch.lastIndexOf(test, value));
            Assert.assertEquals(-1, BinarySearch.lastIndexOf(test, 10));
        }
    }

    @Test
    public void equalOrGreaterIndexOf() {
        int[] test = new int[]{0,2,2,2,5};
        Assert.assertEquals(1, BinarySearch.equalOrGreaterIndexOf(test, 2));
        Assert.assertEquals(1, BinarySearch.equalOrGreaterIndexOf(test, 1));
        Assert.assertEquals(0, BinarySearch.equalOrGreaterIndexOf(test, 0));
        Assert.assertEquals(4, BinarySearch.equalOrGreaterIndexOf(test, 5));
        test = new int[]{2,2,2,2,2};
        Assert.assertEquals(0, BinarySearch.equalOrGreaterIndexOf(test, 2));
        test = new int[]{2,2,3,3,3};
        Assert.assertEquals(2, BinarySearch.equalOrGreaterIndexOf(test, 3));
        Assert.assertEquals(-1, BinarySearch.equalOrGreaterIndexOf(test, 10));
    }

    @Test
    public void equalOrLessLastIndexOf() {
        int[] test = new int[]{0,2,2,2,5};
        Assert.assertEquals(0, BinarySearch.equalOrLessLastIndexOf(test, 0));
        Assert.assertEquals(3, BinarySearch.equalOrLessLastIndexOf(test, 2));
        Assert.assertEquals(4, BinarySearch.equalOrLessLastIndexOf(test, 5));
        Assert.assertEquals(4, BinarySearch.equalOrLessLastIndexOf(test, 10));
        test = new int[]{2,2,2,2,2};
        Assert.assertEquals(4, BinarySearch.equalOrLessLastIndexOf(test, 2));
        test = new int[]{2,2,3,3,3};
        Assert.assertEquals(4, BinarySearch.equalOrLessLastIndexOf(test, 3));
        Assert.assertEquals(4, BinarySearch.equalOrLessLastIndexOf(test, 10));
    }
}