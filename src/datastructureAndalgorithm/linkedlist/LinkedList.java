package datastructureAndalgorithm.linkedlist;

/**
 * 链表接口
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/9 11:12
 * @since 1.0.0
 **/
public interface LinkedList<V> extends Iterable<V> {
    /**
     * 添加节点
     * @param value 节点值
     * @return true 添加成功, false 添加失败
     */
    boolean add(V value);

    /**
     * 从头开始遍历链表，获取指定位置节点的值
     * @param index 节点位置
     * @return 节点的值
     */
    V get(int index);

    /**
     * 删除与指定节点值相同 (即: nodeValue.equals(value) == true) 的所有节点
     * @param value 指定节点的值
     * @return true 有节点被删除, false 没有节点被删除
     */
    boolean remove(V value);

    /**
     * 判断链表是否包含指定值
     * @param value 指定值
     * @return true 包含, false 不包含
     */
    boolean containsValue(V value);

    /**
     * 清空链表
     */
    void clear();

    /**
     * 返回当前链表节点数
     * @return 当前链表节点总数
     */
    int size();

    /**
     * 当前链表是否为空 (即没有任何节点)
     * @return true 链表为空, false 链表不为空
     */
    boolean isEmpty();

    /**
     * @see Object#toString()
     */
    @Override
    String toString();

    /**
     * 判断当前链表是否是回文链表(即: 链表反转后 new.get(i).equals(old.get(i)) == true)
     * @return true 是, false 不是
     */
    boolean isPalindrome();

    /**
     * 链表中的节点
     */
    interface Node<V> {

        /**
         * 当前节点下一个节点
         * @return 下一个节点
         */
        Node<V> next();

        /**
         * 获取值
         * @return 节点值
         */
        V getValue();

        /**
         * 设置值
         * @param value 值
         */
        void setValue(V value);

        /**
         * 与给定对象比较是否相等
         * @param o 给定对象
         * @return true 相等, false 不相等
         */
        @Override
        boolean equals(Object o);
    }

}
