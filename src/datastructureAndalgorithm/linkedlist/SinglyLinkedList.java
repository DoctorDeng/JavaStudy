package datastructureAndalgorithm.linkedlist;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/9 11:35
 * @since 1.0.0
 **/
public class SinglyLinkedList<V> implements LinkedList<V>, Cloneable {
    /**
     * 头节点
     */
    private SinglyNode<V> head;

    /**
     * 尾部节点
     */
    private SinglyNode<V> tail;

    /**
     * 链表节点数
     */
    int size;

    @Override
    public boolean add(V value) {
        checkArgument(value);
        SinglyNode<V> node = newNode(value);
        if (head == null) {
            head = node;
        }
        if (tail == null) {
            tail = head;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    @Override
    public V get(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException("index = " + index);
        }

        int cursor = 0;
        for (V value: this) {
            if (cursor == index) {
                return value;
            }
            cursor++;
        }
        throw new IndexOutOfBoundsException("index = " + index);
    }

    private void checkArgument(V value) {
        if (value == null) {
            throw new NullPointerException("value must not be null");
        }
    }

    @Override
    public boolean remove(V value) {
        boolean removed = false;

        if (!isEmpty()) {
            Iterator<V> iterator = iterator();
            while (iterator.hasNext()) {
                V v = iterator.next();
                if (v != null && v.equals(value)) {
                    iterator.remove();
                    removed = true;
                }
            }
        }
//        if (!isEmpty()) {
//            SinglyNode<V> node = this.head, preNode = null, head = this.head, tail = this.tail;
//
//            while (node != null) {
//                if (node.getValue() == value) {
//                    if (node != head && node != tail) {
//                        preNode.next = node.next;
//                    } else {
//                        if (node == head) {
//                            this.head = head.next;
//                        }
//                        if (node == tail) {
//                            this.tail = preNode;
//                            this.tail.next = null;
//                        }
//                    }
//                    node = node.next;
//                    size--;
//                    removed = true;
//                } else {
//                    preNode = node;
//                    node = node.next;
//                }
//            }
//        }
        return removed;
    }

    @Override
    public boolean containsValue(Object value) {
//        if (!isEmpty()) {
//            SinglyNode<V> node = this.head;
//            while (node != null) {
//              if (node.getValue() == value) {
//                  return true;
//              } else {
//                  node = node.next;
//              }
//            }
//        }
        if (!isEmpty()) {
            for (V v : this) {
                if (v != null && v.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!isEmpty()) {
            for (V value : this) {
                sb.append("{").append(value.toString()).append("},");
            }
        } else {
            sb.append("{},");
        }
        return sb.substring(0, sb.lastIndexOf(","));
    }

    @Override
    public boolean isPalindrome() {
        if (isEmpty()) {
            return false;
        }

        SinglyLinkedList<V> clone = clone();
        // 快指针, 每次前进两个节点
        SinglyNode<V> fast = clone.head;
        // 慢指针, 每次前进一个节点
        SinglyNode<V> slow = clone.head;
        SinglyNode<V> prev = null;

        // 将链表前版半段反转
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            SinglyNode<V> slowNext = slow.next;
            slow.next = prev;
            prev = slow;
            slow = slowNext;
        }
        // 对于基数节点需要额外将慢指针前移一位
        if (fast != null) {
            slow = slow.next;
        }
        // 链表前半部分和后半部分进行比较
        while (slow != null) {
            if (slow.getValue() == null || prev == null || prev.getValue() == null) {
                return false;
            }
           if (!slow.getValue().equals(prev.getValue())) {
               return false;
           }
           slow = slow.next;
           prev = prev.next;
        }
        return true;
    }

    @Override
    public SinglyLinkedList<V> clone() {
        SinglyLinkedList<V> clone = new SinglyLinkedList<>();
        for (V value: this) {
            if (value instanceof Cloneable) {
                clone.add(ObjectUtils.clone(value));
            } else {
                clone.add(value);
            }
        }
        return clone;
    }

    private SinglyNode<V> newNode(V value) {
        return newNode(value, null);
    }

    private SinglyNode<V> newNode(V value, SinglyNode<V> next) {
        return new SinglyNode<>(value, next);
    }

    @Override
    @Nonnull
    public Iterator<V> iterator() {
        return new SinglyIterator();
    }

    /**
     * 单向链表节点
     */
    static class SinglyNode<V> implements LinkedList.Node<V> {
        /**
         * 节点储存的值
         */
        private V value;
        /**
         * 下一个节点, 尾部节点 next = null
         */
        private SinglyNode<V> next;

        public SinglyNode(V value, SinglyNode<V> next) {
            this.value = value;
            this.next  = next;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        public SinglyNode<V> getNext() {
            return next;
        }

        public void setNext(SinglyNode<V> next) {
            this.next = next;
        }
    }
    private class SinglyIterator implements Iterator<V> {

        private SinglyNode<V> cursor = head;

        private SinglyNode<V> prevCursor = null;

        private SinglyNode<V> current = head;

        private SinglyNode<V> prev = null;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public V next() {
            if (cursor == null) {
                throw new NoSuchElementException();
            }
            // 保存当前节点信息
            current = cursor;
            prev = prevCursor;
            // 移动游标到下一个节点
            moveCursor();
            return current.getValue();
        }

        private void moveCursor() {
            prevCursor = cursor;
            cursor = cursor.next;
        }

        @Override
        public void remove() {
            if (current != head && current != tail) {
                prev.next = current.next;
            } else {
                if (current == head) {
                   head = head.next;
                }
                if (current == tail) {
                    tail = prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                }
            }
            size--;
        }
    }

    public static void main(String[] args) {
        testBasic();
        testPalindrome();
    }

    private static void testBasic() {
        LinkedList<Object> linkedList = new SinglyLinkedList<>();

        String str3 =  "3";
        linkedList.add("1");
        linkedList.add(2);
        linkedList.add(str3);

        Assert.isTrue(linkedList.containsValue("1"), "LinkedList.containsValue() test failed");
        Assert.isTrue(linkedList.containsValue(2), "LinkedList.containsValue() test failed");
        Assert.isTrue(linkedList.containsValue("3"), "LinkedList.containsValue() test failed");
        Assert.isTrue("1".equals(linkedList.get(0)), "LinkedList.get() test failed");

        Assert.isTrue("{1},{2},{3}".equals(linkedList.toString()), "LinkedList.toString() test failed");
        linkedList.remove(str3);
        Assert.isTrue("{1},{2}".equals(linkedList.toString()), "LinkedList.remove() test failed");
        linkedList.remove("1");
        Assert.isTrue("{2}".equals(linkedList.toString()), "LinkedList.remove() test failed");
        linkedList.remove(2);
        Assert.isTrue("{}".equals(linkedList.toString()), "LinkedList.remove() test failed");
    }

    private static void testPalindrome() {
        LinkedList<Object> linkedList = new SinglyLinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("2");
        linkedList.add("1");
        Assert.isTrue("{1},{2},{3},{2},{1}".equals(linkedList.toString()), "LinkedList.isPalindrome() not change LinkedList test failed");
        Assert.isTrue(linkedList.isPalindrome(), "LinkedList.isPalindrome() test failed");

        LinkedList<Object> linkedList2 = new SinglyLinkedList<>();
        linkedList2.add("1");
        linkedList2.add("2");
        linkedList2.add("1");
        Assert.isTrue(linkedList2.isPalindrome(), "LinkedList.isPalindrome() test failed");
    }
}
