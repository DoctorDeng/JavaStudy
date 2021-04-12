package datastructureAndalgorithm.sort;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 桶排序.
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/4/12 17:59
 * @since 1.0.0
 */
public class BucketSort extends BasicSort {
    @Override
    public void sort(int[] array) {
        checkArguments(array);
        bucketSort(array, 0, array.length - 1);
    }

    public static void bucketSort(int[] array, int start, int end) {
        checkArguments(array, start, end);
        // 请序列中的最大值和最小值, 用于后续计算桶的数量
        int min = array[start];
        int max = array[start];
        for (int i = start + 1; i <= end; i++) {
            int value = array[i];
            if (value > max) {
                max = value;
            }else if (value < min) {
                min = value;
            }
        }

        if (min == max) {
            return;
        }
        // 初始化桶
        int bucketSize = (max - min)/10 + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketSize);
        for (int i = 0; i <= bucketSize; i++) {
            buckets.add(new ArrayList<>(100));
        }
        // 将元素放入对应的桶中
        for (int i = start; i <= end; i++) {
            int value = array[i];
            int bucketIndex = value/10;
            buckets.get(bucketIndex).add(value);
        }
        // 对桶进行排序，使用插入排序
        for (int i = 0; i < bucketSize; i++) {
            sortBucket(buckets.get(i));
        }
        // 将桶中的元素复制回原序列
        int cursor = start;
        for (int i = 0; i < bucketSize; i++) {
            List<Integer> bucket = buckets.get(i);
            for (Integer integer : bucket) {
                array[cursor] = integer;
                cursor++;
            }
        }
    }

    private static void sortBucket(List<Integer> bucket) {
        if (CollectionUtils.isEmpty(bucket)) {
            return;
        }
        for (int i = 1, len = bucket.size(); i < len; i++) {
            int value = bucket.get(i);
            for (int j = i - 1; j >= 0; j--) {
                if (value < bucket.get(j)) {
                    bucket.set(j + 1, bucket.get(j));
                } else {
                    bucket.set(j + 1, value);
                    break;
                }
            }
        }
    }

    @Override
    public String getName() {
        return "桶排序";
    }

    public static void main(String[] args) {
        new BucketSort().simpleTest(0, 5);
        new BucketSort().largeDataTest(0, 100);
        BasicSort.test(new CountingSort(), 0, 20, 10000000);
    }
}
