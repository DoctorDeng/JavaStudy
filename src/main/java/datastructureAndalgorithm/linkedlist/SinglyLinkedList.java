package datastructureAndalgorithm.linkedlist;

import datastructureAndalgorithm.utils.IterableUtils;
import org.apache.commons.lang3.ObjectUtils;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/9 11:35
 * @since 1.0.0
 **/
public class SinglyLinkedList<V> implements LinkedList<V>, Cloneable {
    /**
     * 头节点
     */
    protected SinglyNode<V> head;

    /**
     * 尾部节点
     */
    protected SinglyNode<V> tail;

    /**
     * 链表节点数
     */
    protected int size;

    @Override
    public boolean add(V value) {
        checkNullAndSize(value);
        SinglyNode<V> node = newNode(value);

        SinglyNode<V> last = tail;
        tail = node;
        if (last == null) {
            head = node;
        } else {
            last.next = node;
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
        for (V value : this) {
            if (cursor == index) {
                return value;
            }
            cursor++;
        }
        throw new IndexOutOfBoundsException("index = " + index);
    }

    protected void checkNullAndSize(V value) {
        // Interger.MAX_VALUE +1 = Interger.MIN_VALUE
        if (size + 1 < 0) {
            throw new OutOfMemoryError();
        }
        if (value == null) {
            throw new NullPointerException("value must not be null");
        }
    }

    @Override
    public boolean remove(V value) {
        return IterableUtils.remove(value, this);
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
    }

    @Override
    public boolean containsValue(V value) {
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
        return IterableUtils.containsValue(value, this);
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
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
        return IterableUtils.toString(this);
    }

    @Override
    public boolean isPalindrome() {
        // 快速失败
        if (isEmpty()) {
            return false;
        }
        // 快速成功
        if (size == 1) {
            return true;
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
        // 对于奇数个数节点链表需要额外将慢指针前移一位
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
    public LinkedList<V> reverse() {
        SinglyLinkedList<V> reverse = clone();
        // 快速返回
        if (isEmpty() || size == 1) {
            return reverse;
        }
        SinglyNode<V> cursor = reverse.head;
        SinglyNode<V> prev = null;
        SinglyNode<V> head = reverse.head;
        SinglyNode<V> tail = reverse.tail;

        // 反转节点
        while (cursor != null) {
            SinglyNode<V> next = cursor.next;
            cursor.next = prev;
            // 移动游标到下一个节点
            prev = cursor;
            cursor = next;
        }
        // 头尾反转
        reverse.tail = head;
        reverse.head = tail;
        return reverse;
    }

    @Override
    public SinglyLinkedList<V> clone() {
        SinglyLinkedList<V> clone = new SinglyLinkedList<>();
        for (V value : this) {
            if (value instanceof Cloneable) {
                clone.add(ObjectUtils.clone(value));
            } else {
                clone.add(value);
            }
        }
        return clone;
    }

    protected SinglyNode<V> newNode(V value) {
        return newNode(value, null);
    }

    protected SinglyNode<V> newNode(V value, SinglyNode<V> next) {
        return new SinglyNode<>(value, next);
    }

    /**
     * 添加头结点
     *
     * @param value 头结点值
     * @return true 添加成功, false 添加失败
     */
    protected boolean addHead(V value) {
        checkNullAndSize(value);

        head = newNode(value, head);
        if (tail == null) {
            tail = head;
        }
        size++;
        return true;
    }

    /**
     * 在指定节点后添加节点
     *
     * @param target 指定节点
     * @param value  要添加的节点的值
     * @return true 添加成功, false 添加失败
     * @throws NoSuchElementException 当 target 为 null 时
     */
    protected boolean addAfter(SinglyNode<V> target, V value) {
        checkNullAndSize(value);

        if (target == null) {
            throw new NoSuchElementException("target node not exists");
        }

        SinglyNode<V> newNode = newNode(value, target.getNext());
        target.setNext(newNode);

        if (tail == target) {
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    @Nonnull
    public Iterator<V> iterator() {
        return new SinglyIterator();
    }

    /**
     * 单向链表节点
     */
    protected static class SinglyNode<V> implements LinkedList.Node<V> {
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
            this.next = next;
        }

        @Override
        public Node<V> next() {
            return next;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SinglyNode<?> that = (SinglyNode<?>) o;
            return value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    protected final class SinglyIterator implements  LinkedListIterator<V> {

        private SinglyNode<V> cursor;

        private SinglyNode<V> current = null;

        private SinglyNode<V> prev = null;

        public SinglyIterator() {
            super();
            this.cursor = head;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public V next() {
            elementCheck();
            prev = current;
            current = cursor;
            cursor = cursor.next;
            return current.getValue();
        }

        @Override
        public void remove() {
            if (current == null) {
                throw new NoSuchElementException();
            }

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
        LinkedListUtils.basicTest(new SinglyLinkedList<>());
        LinkedListUtils.palindromeTest(new SinglyLinkedList<>());
        LinkedListUtils.teverseTest(new SinglyLinkedList<>());
    }
}
