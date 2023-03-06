package datastructureAndalgorithm.practice.offer;

/**
 * 剑指 Offer 24. 反转链表.
 * <pre>
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点.
 * 示例 1：
 *     输入: 1->2->3->4->5->NULL
 *     输出: 5->4->3->2->1->NULL
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof">剑指 Offer 24. 反转链表</a>
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/6 20:31
 */
public class Offer24 {
    class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
    }

    /**
     * 思路：迭代双指针,
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cursor = head;
        ListNode pre = null;
        while (cursor != null) {
            ListNode next = cursor.next;
            cursor.next = pre;
            pre = cursor;
            cursor = next;
        }
        return pre;
    }
}
