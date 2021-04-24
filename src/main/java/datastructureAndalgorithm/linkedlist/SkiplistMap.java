package datastructureAndalgorithm.linkedlist;

import java.util.*;

/**
 * 跳表练习.
 * 该练习是一个简易版本的练习, 更好的实现可参考 {@link java.util.concurrent.ConcurrentSkipListMap}
 * 或者 <a href="https://github.com/redis/redis/blob/unstable/src/t_zset.c">Redis 中的 Skiplist</a> 实现
 * 或者 <a href="https://github.com/MottoX/SkipList">SkipList</a>.
 *
 * @author <a href="http://doctordeng.github.io">DoctorDeng</a>
 * @date 2021/4/17 8:37
 * @since 1.0.0
 */
public class SkiplistMap<K, V> extends AbstractMap<K, V> {
    /**
     * 最大层数
     */
    private static final int MAX_LEVEL = 32;

    private static final double SKIPLIST_P = 0.25;

    private final Random random = new Random();

    private final Comparator<? super K> comparator;

    private HeadIndex<K, V> head;

    private int size = 0;

    public SkiplistMap(Comparator<? super K> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator must not be null");
        }
        this.comparator = comparator;
        head = new HeadIndex<K, V>(new SkiplistNode<K, V>(null, null, null),
                null, null, 1);
    }

    @Override
    public V get(Object key) {
        SkiplistNode<K, V> node = getNode(key);
        if (node != null) {
            return node.value;
        } else {
            return null;
        }
    }

    private SkiplistNode<K, V> getNode(Object key) {
        nullCheck(key);
        for (SkiplistNode<K, V> node = findPre(key).next;;) {
            if (node == null) {
                break;
            }
            if (cpr(comparator, key, node.key) < 0) {
                break;
            }
            if (key.equals(node.key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        nullCheck(key);
        SkiplistNode<K, V> insertNode = null;
        // 插入节点到指定位置
        for (SkiplistNode<K, V> pre = findPre(key), node = pre.next;;) {
            if (node != null) {
                if (key.equals(node.key)) {
                    V old = node.value;
                    node.value = value;
                    return old;
                }
                if (comparator.compare(key, node.key) > 0) {
                    pre = node;
                    node = node.next;
                    continue;
                }
            }
            insertNode = new SkiplistNode<>(key, value, node);
            pre.next = insertNode;
            break;
        }
        // 随机层数
        int newLevel = randomLevel();
        int oldLevel = head.level;
        Index<K, V> insertTopIndex = null;
        // 当随机层数(newLevel) 小于跳表当前最大层数时，为插入元素创建 newLevel 层
        if (newLevel <= oldLevel) {
            for (int i = 1; i <= newLevel; i++) {
                insertTopIndex = new Index<>(insertNode, insertTopIndex, null);
            }
        }
        // 随机层数大于跳表当前最大层数时，需要增长层
        else {
            // 每次最多增加一层
            newLevel = oldLevel + 1;
            Index<K, V>[] indexs = (Index<K,V>[]) new Index<?, ?>[newLevel + 1];

            // 为插入元素创建层，数量为最大层数
            for (int i = 1; i <= newLevel; ++i) {
                insertTopIndex = new Index<>(insertNode, insertTopIndex, null);
                indexs[i] = insertTopIndex;
            }

            // 头节点层增长, 新层指向插入元素对应的层级的层
            HeadIndex<K, V> newHead = head;
            SkiplistNode<K, V> oldNode = head.node;
            for (int j = oldLevel + 1; j <= newLevel; ++j) {
                newHead = new HeadIndex<>(oldNode, newHead, indexs[j], j);
            }
            head = newHead;
        }

        // 将插入元素的各层与离该层最近的对应层级的层（链接层）进行链接
        int insertCursorLevel = newLevel;
        int cursorLevel = head.level;
        for (Index<K, V> current = head, right = current.right, insertIndex = insertTopIndex;;) {
            if (current == null || insertIndex == null) {
                break;
            }
            // 找到离插入层最近的链接层
            if (right != null) {
                SkiplistNode<K, V> rightNode = right.node;
                if (cpr(comparator, key, rightNode.key) > 0) {
                    current = right;
                    right = right.right;
                    continue;
                }
            }

            // 将插入层与离该层最近的链接层链接
            if (insertCursorLevel == cursorLevel) {
                current.right = insertIndex;
                if (right != insertIndex) {
                    insertIndex.right = right;
                }
                if (--insertCursorLevel == 0) {
                    break;
                }
            }
            // 完成链接后插入层下移，进行下一层的链接
            if (--cursorLevel >= insertCursorLevel && cursorLevel < newLevel) {
                insertIndex = insertIndex.down;
            }
            // 链接层下移
            current = current.down;
            right = current.right;
        }
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        if (key == null) {
            return null;
        }

        SkiplistNode<K, V> deleteNode = null;
        // 删除节点
        for (SkiplistNode<K, V> pre = findPre(key), node = pre.next; ; ) {
            if (node == null) {
                break;
            }
            if (cpr(comparator, key, node.key) < 0) {
                break;
            }
            if (key.equals(node.key)) {
                pre.next = node.next;
                deleteNode = node;
                break;
            }
            pre = node;
            node = node.next;
        }
        if (deleteNode == null) {
            return null;
        }
        // 删除与节点相关的层
        for (Index<K, V> pre = head, current = head.right, down;;) {
            if (current != null) {
                if (key.equals(current.node.key)) {
                    pre.right = current.right;
                }
                if (cpr(comparator, key, current.node.key) >= 0) {
                    pre = current;
                    current = current.right;
                    continue;
                }
            }
            down = pre.down;
            if (down == null) {
                break;
            }
            pre = down;
            current = down.right;
        }

        if (head.right == null) {
            deleteUselessLevel();
        }

        size--;
        return deleteNode.value;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    /**
     * 采用 Redis zset 中随机层数算法, 具体见: t_zset.c#zslRandomLevel() 方法, 源码如下：
     * <pre>{@code
     *      // 其中 ZSKIPLIST_MAXLEVEL = 32, ZSKIPLIST_P = 0.25
     *      int zslRandomLevel(void) {
     *          int level = 1;
     *          while ((random()&0xFFFF) < (ZSKIPLIST_P * 0xFFFF))
     *              level += 1;
     *          return (level<ZSKIPLIST_MAXLEVEL) ? level : ZSKIPLIST_MAXLEVEL;
     *      }
     *  }</pre>
     * @return 随机层数
     */
    private int randomLevel() {
        int newLevel = 1;
        while ((random.nextInt() & 0xFFFF) < (SKIPLIST_P * 0xFFFF)) {
            newLevel++;
        }
        return Math.min(newLevel, MAX_LEVEL);
    }

    private void nullCheck(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    // 查找最后一个小于给定 key 的元素, 如果没有则返回头部元素
    private SkiplistNode<K, V> findPre(Object key) {
        nullCheck(key);

        for (Index<K, V> currentIdx = head, rightIdx = currentIdx.right, down; ; ) {
            // 当不为最后一个元素时，如果当前元素比给定 key 下则当前元素右移
            if (rightIdx != null) {
                if (cpr(comparator, key, rightIdx.node.key) > 0) {
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

    @Override
    public int size() {
        return size;
    }

    private SkiplistNode<K, V> findFirstNode() {
        return head.node.next;
    }

    private EntryIterator entryIterator() {
        return new EntryIterator(findFirstNode());
    }

    static final int cpr(Comparator c, Object x, Object y) {
        return (c != null) ? c.compare(x, y) : ((Comparable)x).compareTo(y);
    }

    static class SkiplistNode<K, V> implements Map.Entry<K, V> {

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

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
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

    private class EntryIterator implements Iterator<Map.Entry<K, V>> {
        private SkiplistNode<K, V> currentCursor;

        EntryIterator(SkiplistNode<K, V> currentCursor) {
            this.currentCursor = currentCursor;
        }

        @Override
        public boolean hasNext() {
            return currentCursor != null && currentCursor.next != null;
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Map.Entry<K, V> next = currentCursor;
            currentCursor = currentCursor.next;
            return next;
        }
    }

    private class EntrySet extends AbstractSet<Entry<K, V>> {
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return entryIterator();
        }

        @Override
        public int size() {
            return SkiplistMap.this.size();
        }
    }

    private void deleteUselessLevel() {
        HeadIndex<K, V> top = head;
        HeadIndex<K, V> secondTop;
        HeadIndex<K, V> thirdTop;

        if (top.level > 3
                && (secondTop = (HeadIndex<K, V>) top.down) != null
                && (thirdTop = (HeadIndex<K, V>) secondTop.down) != null
                && top.right == null
                && secondTop.right == null
                && thirdTop.right == null
        )  {
            head = thirdTop;
        }
    }

    public static void main(String[] args) {
        SkiplistMap<Integer, String> skiplistMap = new SkiplistMap<>(Integer::compareTo);
        int max = 0;
        for (int i = 0; i < 100000; i++) {
            int level = skiplistMap.randomLevel();
            if (level > max) {
                max = level;
            }
        }
        System.out.println(max);
    }
}
