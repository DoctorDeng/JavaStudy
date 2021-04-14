package datastructureAndalgorithm;

import org.junit.Assert;
import org.junit.Test;

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
}