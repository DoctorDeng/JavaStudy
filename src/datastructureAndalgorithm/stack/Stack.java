package datastructureAndalgorithm.stack;

import java.util.Iterator;

/**
 * 堆栈接口
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/14 20:17
 * @since 1.0.0
 */
public interface Stack<E> extends Iterable<E> {

    /**
     * 将元素放入堆栈的顶部
     * @param element 要入栈的元素
     * @return element 入栈元素
     */
    E push(E element);

    /**
     * 删除堆栈顶部的元素，并将该对象作为此函数的值返回
     * @return 堆栈顶部的元素
     */
    E pop();

    /**
     * 查看堆栈顶部的元素且不会将其从堆栈中移除
     * @return 堆栈顶部的元素
     */
    E peek();

    /**
     * 判断当前堆栈是否为空(即没有储存任何元素)
     * @return true 堆栈为空, false 堆栈不为空
     */
    boolean isEmpty();

    /**
     * 返回当前堆栈储存的元素个数
     * @return 堆栈储存的元素数量
     */
    int size();
}
