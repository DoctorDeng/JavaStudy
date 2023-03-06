package datastructureAndalgorithm.practice.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 35. 复杂链表的复制.
 * <pre>
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null.
 * 示例 1：
 *     输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *     输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof">剑指 Offer 35. 复杂链表的复制</a>
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/6 20:53
 */
public class Offer35 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        /**
         * 思路：使用哈希表做原节点和克隆节点映射, 第一轮遍历原链表对所有节点进行简单克隆, 第二遍遍历克隆链表, 根据哈希表将节点 random 设置为克隆后的节点.
         *
         * 时间复杂度 O(N) ： 两轮遍历链表，使用 O(N) 时间。
         * 空间复杂度 O(N) ： 哈希表使用线性大小的额外空间
         */
        public Node copyRandomListByHash(Node head) {
            if (head == null) {
                return null;
            }
            Map<Node, Node> cache = new HashMap<>(1000);
            Node cloneHead = simpleClone(head);
            cache.put(head, cloneHead);
            Node cursor = head.next;
            Node preClone = cloneHead;
            while (cursor != null) {
                Node clone = simpleClone(cursor);
                cache.put(cursor, clone);

                preClone.next = clone;
                preClone = clone;
                cursor = cursor.next;
            }
            cursor = cloneHead;
            while (cursor != null) {
                Node random = cursor.random;
                if (random != null) {
                    cursor.random = cache.get(random);
                }
                cursor = cursor.next;
            }
            return cloneHead;
        }

        private Node simpleClone(Node node) {
            Node clone = new Node(node.val);
            clone.random = node.random;
            return clone;
        }

        /**
         * 哈希表法简洁版.
         */
        public Node copyRandomListByHashSimpleCode(Node head) {
            if (head == null) {
                return null;
            }
            Map<Node, Node> cache = new HashMap<>(1000);
            Node cursor = head;
            while (cursor != null) {
                cache.put(cursor, new Node(cursor.val));
                cursor = cursor.next;
            }
            cursor = head;
            while (cursor != null) {
                Node clone = cache.get(cursor);
                clone.next = cache.get(cursor.next);
                clone.random = cache.get(cursor.random);
                cursor = cursor.next;
            }
            return cache.get(head);
        }

        /**
         * 思路: 考虑构建 原节点 1 -> 克隆节点 1 -> 原节点 2 -> 克隆节点 2 -> …… 的拼接链表，
         * 如此便可在访问原节点的 random 指向节点的同时找到新对应新节点的 random 指向节点.
         */
        public Node copyRandomListByLinkedListSplicing(Node head) {
            if (head == null) {
                return null;
            }
            // 1. 遍历链表, 克隆原节点, 并将原链表拼接为: 原节点 1 -> 克隆节点 1 -> 原节点 2 -> 克隆节点 2 -> …….
            Node cursor = head;
            while (cursor != null) {
                Node clone = new Node(cursor.val);
                Node next = cursor.next;
                cursor.next = clone;
                clone.next = next;
                cursor = next;
            }
            // 2. 遍历链表, 将克隆节点 random 指向克隆后的节点.
            cursor = head;
            while (cursor != null) {
                Node random = cursor.random;
                if (random != null) {
                    cursor.next.random = random.next;
                }
                cursor = cursor.next.next;
            }
            // 3. 将链表拆分为: 克隆后的新链表和原链表, 即 原节点 1 -> 克隆节点 1 -> 原节点 2 -> 克隆节点 2 -> ……  变为:
            // * 原节点 1 -> 原节点 2 -> ....
            // * 克隆节点 1 -> 克隆节点 2 -> ....
            cursor = head.next;
            Node pre = head, cloneHead = head.next;
            while(cursor.next != null) {
                pre.next = pre.next.next;
                cursor.next = cursor.next.next;
                pre = pre.next;
                cursor = cursor.next;
            }
            pre.next = null;
            return cloneHead;
        }

    }

}
