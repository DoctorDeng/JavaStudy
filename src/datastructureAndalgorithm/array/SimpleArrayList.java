package datastructureAndalgorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 简易的 ArrayList 练习
 * @author <a href="http://doctordeng.github.io/">DoctorDeng</a>
 * @version 1.0
 * @date 2020/12/31 14:52
 */
public class SimpleArrayList {
    /**
     * 默认容量: 10
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 空数组，当指定初始容量为 0 时使用
     */
    private static final Object[] EMPTY_ARRAY = {};
    /**
     * 默认空数组，当初始化未指定初始容量使用
     */
    private static final Object[] DEFAULT_EMPTY_ARRAY = {};
    /**
     * 储存元素的数组
     */
    private Object[] elements;
    /**
     * 存储的元素数量
     */
    private int size;

    public SimpleArrayList() {
        this.elements = DEFAULT_EMPTY_ARRAY;
    }

    public SimpleArrayList(int initCapacity) {
       if (initCapacity < 0) {
           throw new IllegalArgumentException("InitCapacity must be greater than or equal to 0");
       } else if (initCapacity == 0) {
           this.elements = EMPTY_ARRAY;
       } else {
           this.elements = new Object[initCapacity];
       }
    }

    public boolean add(Object element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
        return true;
    }

    public boolean add(int index, Object element) {
        rangeCheck(index);

        ensureCapacity(size + 1);

        System.arraycopy(elements, index,
                elements, index + 1, size - index);

        this.elements[index] = element;
        size++;
        return true;
    }

    public Object get(int index) {
        rangeCheck(index);

        return elements[index];
    }

    public Object remove(int index) {
        rangeCheck(index);

        Object removeElement = this.elements[index];

        int moveNum = size - index - 1;
        if (moveNum > 0) {
            System.arraycopy(elements, index + 1,
                    elements, index, moveNum);
        }
        this.elements[--size] = null;
        return removeElement;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (this.elements == DEFAULT_EMPTY_ARRAY) {
            minCapacity = Math.max(minCapacity, DEFAULT_CAPACITY);
        }
        if (minCapacity > elements.length) {
            dynamicExpansion(minCapacity);
        }
    }

    private void dynamicExpansion(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }

        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity < 0) {
            newCapacity = Integer.MAX_VALUE;
        } else if (minCapacity > newCapacity) {
            newCapacity = minCapacity;
        }

        this.elements = Arrays.copyOf(elements, newCapacity);
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.elements.length;
    }

    public static void main(String[] args) {
        List<Integer> aaa = new ArrayList<>();
        aaa.add(1);
        System.out.println(aaa.get(-1));

//        SimpleArrayList simpleArrayList = new SimpleArrayList(10000);
//        for (int i = 0; i < 20; i++) {
//            simpleArrayList.add(i);
//        }
//        System.out.println(simpleArrayList.toString());
//        simpleArrayList.remove(0);
//        System.out.println(simpleArrayList.size());
//        simpleArrayList.remove(16);
//        System.out.println(simpleArrayList.size());
    }

    @Override
    public String toString() {
        return "SimpleArrayList{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }
}
