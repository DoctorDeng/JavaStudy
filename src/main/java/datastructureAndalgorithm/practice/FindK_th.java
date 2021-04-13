package datastructureAndalgorithm.practice;

import datastructureAndalgorithm.sort.QuickSort;
import datastructureAndalgorithm.sort.SortUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.util.Assert;
import util.PrintUtil;

import java.util.Arrays;
import java.util.Stack;

/**
 * 在时间复杂度 O(n) 内在一个无序数组中查找第 K 大的元素（如果值相同下标大的元素更大）.
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/4/3 10:32
 * @since 1.0.0
 */
public class FindK_th extends QuickSort {

    public static int findK_th(int[] array, int k) {
        checkArguments(array);

        if (k < 0 || k > array.length) {
            throw new RuntimeException("k cannot be greater than array length or less than 0, k =" + k);
        }

        return findK_th(array, k, 0, array.length - 1);
    }

    private static int findK_th(int[] array, int k, int start, int end) {
        Stack<Integer> stack = new Stack<>();

        stack.push(start);
        stack.push(end);

        int expectKIndex = k - 1;

        while (!stack.isEmpty()) {
            int _end = stack.pop();
            int _start = stack.pop();

            int pivot = lowPartition(array, _start, _end);
            if (pivot == expectKIndex) {
                return array[pivot];
            } else if (pivot > expectKIndex) {
                stack.push(_start);
                stack.push(pivot - 1);
            } else {
                stack.push(pivot + 1);
                stack.push(_end);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int size = RandomUtils.nextInt(10 , 100);
            int k =  size/2;
            int[] array = SortUtils.getTestData(size);
            testFindKTh(array, k);
        }
    }

    public static void testFindKTh(int[] array, int k) {
        PrintUtil.print(array);
        int findK = findK_th(array, k);
        Arrays.sort(array);
        PrintUtil.print(array);
        int expectK = array[k - 1];
        System.out.println("find k:" + findK + " expect k:" + expectK);
        Assert.isTrue( findK == expectK, "Failed to find the k-th largest element in the array");
    }
}
