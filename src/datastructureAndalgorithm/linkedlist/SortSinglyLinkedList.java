package datastructureAndalgorithm.linkedlist;


import org.springframework.util.Assert;

/**
 * 有序单向链表
 *
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/11 9:46
 * @since 1.0.0
 */
public class SortSinglyLinkedList<V extends Comparable<V>> extends SinglyLinkedList<V> {
    /**
     * 排序模式, true 正序(从小到大), false 反序 (从大到小)
     */
    private boolean positiveOrder = true;

    public SortSinglyLinkedList() {
        super();
    }

    public SortSinglyLinkedList(boolean positiveOrder) {
        super();
        this.positiveOrder = positiveOrder;
    }

    @Override
    public boolean add(V value) {
        checkNullAndSize(value);

        if (isEmpty()) {
            return super.add(value);
        }
        SinglyNode<V> cursor = head;
        while (cursor != null) {
            SinglyNode<V> cursorNext = cursor.getNext();
            if (positiveOrder) {
                if (cursorNext == null) {
                    if (value.compareTo(cursor.getValue()) < 0) {
                        return addHead(value);
                    } else {
                        return super.add(value);
                    }
                }

                if (value.compareTo(cursor.getValue()) >= 0
                        && value.compareTo(cursorNext.getValue()) <= 0
                ) {
                    return addAfter(cursor, value);
                }
            } else {
                if (cursorNext == null) {
                    if (value.compareTo(cursor.getValue()) > 0) {
                        return addHead(value);
                    } else {
                        return super.add(value);
                    }
                }

                if (value.compareTo(cursor.getValue()) <= 0
                        && value.compareTo(cursorNext.getValue()) >= 0
                ) {
                    return addAfter(cursor, value);
                }
            }
            cursor = cursorNext;
        }
        // 不会发生
        size++;
        return true;
    }

    public static void main(String[] args) {
        testAdd();
        testAescSort();
        testDescSort();
    }

    private static  void testAdd() {
        SortSinglyLinkedList<Integer> sortSinglyLinkedList = new SortSinglyLinkedList<>();
        sortSinglyLinkedList.add(1);
        Assert.isTrue(1 == sortSinglyLinkedList.size, "SortSinglyLinkedList.add()  test failed");
        sortSinglyLinkedList.add(2);
        Assert.isTrue(2 == sortSinglyLinkedList.size, "SortSinglyLinkedList.add()  test failed");
        sortSinglyLinkedList.add(3);
        Assert.isTrue(3 == sortSinglyLinkedList.size, "SortSinglyLinkedList.add()  test failed");
    }

    private static void testAescSort() {
        SortSinglyLinkedList<Integer> sortSinglyLinkedList = new SortSinglyLinkedList<>();
        sortSinglyLinkedList.add(3);
        Assert.isTrue("{3}".equals(sortSinglyLinkedList.toString()), "SortSinglyLinkedList.add() aesc sort  test failed");
        sortSinglyLinkedList.add(2);
        Assert.isTrue("{2},{3}".equals(sortSinglyLinkedList.toString()), "SortSinglyLinkedList.add() aesc sort test failed");
        sortSinglyLinkedList.add(5);
        Assert.isTrue("{2},{3},{5}".equals(sortSinglyLinkedList.toString()), "SortSinglyLinkedList.add() aesc sort test failed");
        sortSinglyLinkedList.add(1);
        Assert.isTrue("{1},{2},{3},{5}".equals(sortSinglyLinkedList.toString()), "SortSinglyLinkedList.add() aesc sort test failed");
    }
    private static void testDescSort() {
        SortSinglyLinkedList<Integer> sortSinglyLinkedList = new SortSinglyLinkedList<>(false);
        sortSinglyLinkedList.add(3);
        Assert.isTrue("{3}".equals(sortSinglyLinkedList.toString()), "SortSinglyLinkedList.add() desc sort test failed");
        sortSinglyLinkedList.add(2);
        Assert.isTrue("{3},{2}".equals(sortSinglyLinkedList.toString()), "SortSinglyLinkedList.add() desc sort test failed");
        sortSinglyLinkedList.add(5);
        Assert.isTrue("{5},{3},{2}".equals(sortSinglyLinkedList.toString()), "SortSinglyLinkedList.add() desc sort test failed");
        sortSinglyLinkedList.add(1);
        Assert.isTrue("{5},{3},{2},{1}".equals(sortSinglyLinkedList.toString()), "SortSinglyLinkedList.add() desc sort test failed");
    }
}
