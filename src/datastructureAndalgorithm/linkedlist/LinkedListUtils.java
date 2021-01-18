package datastructureAndalgorithm.linkedlist;

import org.springframework.util.Assert;

/**
 * 链表工具类
 *
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/18 21:17
 * @since 1.0.0
 */
public class LinkedListUtils {
    /**
     * 链表基础测试
     * @param linkedList 链表实例
     */
    public static void basicTest(LinkedList<Object> linkedList) {
        Assert.notNull(linkedList, "LinkedList must not be null");
        String str3 = "3";
        Assert.isTrue(linkedList.size() ==  0,"LinkedList.size() test failed");
        linkedList.add("1");
        Assert.isTrue(linkedList.size() ==  1, "LinkedList.size() test failed");
        linkedList.add(2);
        Assert.isTrue(linkedList.size() ==  2, "LinkedList.size() test failed");
        linkedList.add(str3);
        Assert.isTrue(linkedList.size() ==  3, "LinkedList.size() test failed");

        Assert.isTrue(linkedList.containsValue("1"), "LinkedList.containsValue() test failed");
        Assert.isTrue(linkedList.containsValue(2), "LinkedList.containsValue() test failed");
        Assert.isTrue(linkedList.containsValue("3"), "LinkedList.containsValue() test failed");
        Assert.isTrue("1".equals(linkedList.get(0)), "LinkedList.get() test failed");

        Assert.isTrue("{1},{2},{3}".equals(linkedList.toString()), "LinkedList.toString() test failed");
        linkedList.remove(str3);
        Assert.isTrue("{1},{2}".equals(linkedList.toString()), "LinkedList.remove() test failed");
        linkedList.remove("1");
        System.out.println(linkedList.toString());
        Assert.isTrue("{2}".equals(linkedList.toString()), "LinkedList.remove() test failed");
        linkedList.remove(2);
        Assert.isTrue("{}".equals(linkedList.toString()), "LinkedList.remove() test failed");
    }
    public static void palindromeTest(LinkedList<Object> linkedList) {
        Assert.notNull(linkedList, "LinkedList must not be null");
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

    public static void teverseTest(LinkedList<Object> linkedList) {
        Assert.notNull(linkedList, "LinkedList must not be null");
        LinkedList<Object> reverse1 = linkedList.reverse();
        Assert.isTrue("{}".equals(reverse1.toString()), "LinkedList.reverse() test failed");

        linkedList.add("1");
        LinkedList<Object> reverse2 = linkedList.reverse();
        Assert.isTrue("{1}".equals(reverse2.toString()), "LinkedList.reverse() test failed");

        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        LinkedList<Object> reverse = linkedList.reverse();
        Assert.isTrue("{5},{4},{3},{2},{1}".equals(reverse.toString()), "LinkedList.reverse() test failed");
    }
}
