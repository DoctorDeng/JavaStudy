package datastructureAndalgorithm.practice.offer;

/**
 * 剑指 Offer 06. 从尾到头打印链表.
 * <pre>
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）.
 * 示例 1：
 *     输入：head = [1,3,2]
 *     输出：[2,3,1]
 *     限制：0 <= 链表长度 <= 10000
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof">剑指 Offer 06. 从尾到头打印链表</a>
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/6 20:18
 */
public class Offer06 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
     }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        if (head.next == null) {
            return new int[]{head.val};
        }
        int size = 0;
        ListNode cursor = head;
        ListNode pre = null;
        while (cursor != null) {
            ListNode next = cursor.next;
            cursor.next = pre;
            pre = cursor;
            cursor = next;
            size++;
        }
        int[] values = new int[size];
        int index = 0;
        while (pre != null) {
            values[index] = pre.val;
            index++;
            pre = pre.next;
        }
        return values;
    }
}
