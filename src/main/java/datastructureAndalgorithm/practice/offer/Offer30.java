package datastructureAndalgorithm.practice.offer;

/**
 * 剑指 Offer 30. 包含min函数的栈.
 * <pre>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1).
 * 示例:
 *  MinStack minStack = new MinStack();
 *  minStack.push(-2);
 *  minStack.push(0);
 *  minStack.push(-3);
 *  minStack.min();   --> 返回 -3.
 *  minStack.pop();
 *  minStack.top();   --> 返回 0.
 *  minStack.min();   --> 返回 -2.
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/">剑指 Offer 30. 包含min函数的栈</a>
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/6 19:18
 */
public class Offer30 {
}

class MinStack {

    /**
     * initialize your data structure here.
     */
    public MinStack() {
    }

    Entry head;

    public void push(int x) {
        if (head == null) {
            head = new Entry(x, x, null);
        }
        else {
            int min = x < head.min ? x : head.min;
            head = new Entry(x, min, head);
        }
    }

    public void pop() {
        assetStackIsNotEmpty();
        head = head.next;
    }

    public int top() {
        assetStackIsNotEmpty();
        return head.value;
    }

    public int min() {
        assetStackIsNotEmpty();
        return head.min;
    }

    private void assetStackIsNotEmpty() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }
    }

    static class Entry {

        int value;

        int min;

        Entry next;

        Entry(int value, int min, Entry next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }

    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
