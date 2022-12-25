package datastructureAndalgorithm.practice.leetcode;

/**
 * Leetcode 2 题:
 * <pre>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字.
 * 请你将两个数相加，并以相同形式返回一个表示和的链表.
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头.
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/add-two-numbers">Leetcode 2: 两数相加</a>
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/12/24 20:27
 */
public class Leetcode2 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Cursor = l1;
        ListNode l2Cursor = l2;
        ListNode head = new ListNode();
        ListNode cursor = head;

        // 进位值, 例如: 5 + 6 = 1 要向高位进 1.
        int remainder = 0;
        while (l1Cursor != null || l2Cursor != null) {
            int val = remainder;
            if (l1Cursor != null) {
                val = l1Cursor.val + val;
                l1Cursor = l1Cursor.next;
            }
            if (l2Cursor != null) {
                val = l2Cursor.val + val;
                l2Cursor = l2Cursor.next;
            }
            cursor.next = new ListNode(val % 10);
            remainder = val / 10;
            cursor = cursor.next;
        }
        if (remainder != 0) {
            cursor.next = new ListNode(remainder);
        }
        return head.next;
    }

}
