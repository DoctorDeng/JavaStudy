package datastructureAndalgorithm.stack;

import org.springframework.util.Assert;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * 基于数组实现的固定容量大小的堆栈
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/14 20:33
 * @since 1.0.0
 */
public class FixedArrayStack<E> implements Stack<E> {

    private static final int DEFAULT_CAPACIY = 10;

    /**
     * 当前堆栈元素数量
     */
    private int size;

    /**
     * 堆栈容量
     */
    private int capacity;

    /**
     * 储存堆栈元素的数组
     */
    private Object[] elements;

    public FixedArrayStack(int capacity) {
        super();
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than or equal to 0");
        }
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    public FixedArrayStack() {
        super();
        this.capacity = DEFAULT_CAPACIY;
        elements = new Object[capacity];
    }

    @Override
    public E push(E element) {
        checkCapacity();
        elements[size++] = element;
        return element;
    }

    private void checkCapacity() {
        if (size >= capacity) {
            throw new OutOfMemoryError("Stack full");
        }
    }

    private void emptyCheck() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
    }

    @Override
    public E pop() {
        emptyCheck();
        E element =  peek();
        removeElementAt(size() - 1);
        return element;
    }

    private void removeElementAt(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException(index + " >= " + size());
        }
        else if (index < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        int movedNum = size() - index - 1;
        if (movedNum > 0) {
            System.arraycopy(elements, index + 1, elements, index, movedNum);
        }
        elements[index] = null;
        size--;
    }

    @Override
    public E peek() {
        emptyCheck();
        int index = size() - 1;
        return (E) elements[index];
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    class Itr<E> implements Iterator<E> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

    public static void main(String[] args) {
        basicTest();
    }

    private static void basicTest() {
        Stack<Integer> stack = new FixedArrayStack<>(5);
        stack.push(1);
        Assert.isTrue(!stack.isEmpty() && stack.size() == 1, "FixedArrayStack#push() test failed");
        Assert.isTrue(stack.peek().equals(1), "FixedArrayStack#peek() test failed");
        stack.push(2);
        Assert.isTrue(stack.pop().equals(2), "FixedArrayStack#pop() test failed");
        Assert.isTrue(!stack.isEmpty() && stack.size() == 1, "FixedArrayStack#pop() test failed");

    }
}
