package datastructureAndalgorithm.linkedlist;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 跳表练习.
 * 该练习是一个简易版本的练习, 更好的实现可参考 {@link java.util.concurrent.ConcurrentSkipListMap}
 * 或者 <a href="https://github.com/redis/redis/blob/unstable/src/t_zset.c">Redis 中的 Skiplist</a> 实现.
 *
 * @author <a href="http://doctordeng.github.io">DoctorDeng</a>
 * @date 2021/4/17 8:37
 * @since 1.0.0
 */
public class Skiplist<K, V> {

    private Comparator<? super K> comparator;

    private HeadIndex<K, V> head;

    public Skiplist(Comparator<? super K> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator must not be null");
        }
        this.comparator = comparator;
        head = new HeadIndex<K, V>(new SkiplistNode<K, V>(null, null, null),
                null, null, 1);
    }


    public V get(K key) {
        nullCheck(key);
        SkiplistNode<K, V> lastLess = findPre(key);
        if (lastLess.isHeader()) {
            return null;
        }
        SkiplistNode<K, V> current = lastLess;
        while (current != null) {
            if (key.equals(current.key)) {
                return current.value;
            } else {
                current = current.next;
            }
        }
        return null;
    }

    public V put(K key, V value) {
        nullCheck(key);

        SkiplistNode<K, V> pre = findPre(key);


        return value;
    }


    private void nullCheck(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }


    // 查找最后一个小于给定 key 的元素, 如果没有则返回头部元素
    private SkiplistNode<K, V> findPre(K key) {
        nullCheck(key);

        for (Index<K, V> currentIdx = head, rightIdx = currentIdx.right, down ; ; ) {
            // 当不为最后一个元素时，如果当前元素比给定 key 下则当前元素右移
            if (rightIdx != null) {
                SkiplistNode<K, V> node = rightIdx.node;
                if (comparator.compare(key, node.key) > 0) {
                    currentIdx = rightIdx;
                    rightIdx = rightIdx.right;
                    continue;
                }
            }
            // 遍历到最后一个元素或者 key 比当前元素小, 需要层级下移
            down = currentIdx.down;
            // 如果当前层级是第一层直接返回当前层对应的元素
            if (down == null) {
                return currentIdx.node;
            }
            // 层级下移
            currentIdx = down;
            rightIdx = down.right;
        }

    }


    static class SkiplistNode<K, V> {

        final K key;

        V value;

        SkiplistNode<K, V> next;

        public SkiplistNode(K key, V value, SkiplistNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public SkiplistNode(SkiplistNode<K, V> next) {
            this.key = null;
            this.value = null;
            this.next = next;
        }

        public boolean isHeader() {
            return key == null;
        }
    }

    static class Index<K, V> {
        final SkiplistNode<K, V> node;
        Index<K, V> down;
        Index<K, V> right;

        Index(SkiplistNode<K, V> node, Index<K, V> down, Index<K, V> right) {
            this.node = node;
            this.down = down;
            this.right = right;
        }
    }

    static class HeadIndex<K, V> extends Index<K, V> {
        int level;

        HeadIndex(SkiplistNode<K, V> node, Index<K, V> down, Index<K, V> right, int level) {
            super(node, down, right);
            this.level = level;
        }
    }

    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, String> concurrentSkipListMap = new ConcurrentSkipListMap<>(Integer::compareTo);
        concurrentSkipListMap.put(1111, "222");
        concurrentSkipListMap.put(1111, "333");
        concurrentSkipListMap.forEach((key, value) -> System.out.println("key is: " + key + " value is:" + value));
    }
}
