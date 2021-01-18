package datastructureAndalgorithm.linkedlist;

import datastructureAndalgorithm.utils.IterableUtils;
import org.apache.commons.lang3.ObjectUtils;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向链表实现
 *
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/18 19:15
 * @since 1.0.0
 */
public class DoublyLinkedList<V> implements LinkedList<V>, Cloneable {
    /**
     * 头节点
     */
    private DoublyNode<V> head;
    /**
     * 尾节点
     */
    private DoublyNode<V> tail;

    /**
     * 链表元素个数
     */
    private int size;

    @Override
    public boolean add(V value) {
        checkNullAndSize(value);

        if (head == null) {
            head = newNode(value);
        }

        if (tail == null) {
            tail = head;
        } else {
            DoublyNode<V> node = newNode(value, tail, null);
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    @Override
    public V get(int index) {
        if (index >= 0 && index <= (size -1)) {
            int curosr = 0;
            for (V v : this) {
                if (curosr == index) {
                    return v;
                }
                curosr++;
            }
        }
        throw new IndexOutOfBoundsException("index = " + index);
    }

    @Override
    public boolean remove(V value) {
        return IterableUtils.remove(value, this);
    }

    @Override
    public boolean containsValue(V value) {
        return IterableUtils.containsValue(value, this);
    }

    protected DoublyNode<V> newNode(V value) {
        return new DoublyNode<>(value, null, null);
    }

    protected DoublyNode<V> newNode(V value, DoublyNode<V> prev, DoublyNode<V> next) {
        return new DoublyNode<>(value, prev, next);
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
    public boolean isPalindrome() {
        return false;
    }

    @Override
    public LinkedList<V> reverse() {
        return null;
    }

    @Override
    @Nonnull
    public Iterator<V> iterator() {
        return new DoublyIterator();
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
    protected final DoublyLinkedList<V> clone() throws CloneNotSupportedException {
        DoublyLinkedList<V> clone = new DoublyLinkedList<>();
        if (!isEmpty()) {
            for (V value: this) {
                if (value instanceof Cloneable) {
                    clone.add(ObjectUtils.clone(value));
                } else {
                    clone.add(value);
                }
            }
        }
        return clone;
    }

    @Override
    public String toString() {
        return IterableUtils.toString(this);
    }

    protected final static class DoublyNode<V> implements LinkedList.Node<V> {
        /**
         * 节点储存的值
         */
        private V value;

        /**
         * 上一个节点
         */
        private DoublyNode<V> prev = null;
        /**
         * 下一个节点
         */
        private DoublyNode<V> next = null;

        public  DoublyNode(V value, DoublyNode<V> prev, DoublyNode<V> next) {
            super();
            this.value = value;
            this.prev  = prev;
            this.next  = next;
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
    }

    protected final class DoublyIterator implements LinkedListIterator<V> {
        private DoublyNode<V> current;

        private DoublyNode<V> cursor;

        public DoublyIterator() {
            super();
            cursor = head;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public V next() {
            elementCheck();
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
                current.prev.next = current.next;
            } else {
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    }
                }
                if (current == tail) {
                    tail = tail.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                }
            }
            size--;
        }
    }

    public static void main(String[] args) {
        LinkedListUtils.basicTest(new DoublyLinkedList<Object>());
    }
}
