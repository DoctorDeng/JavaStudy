package concurrent.locks;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 简易的 CLH 锁实现.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/3/1 18:17
 */
public class SimpleCLHLock implements SimpleLock {

	private static final ThreadLocal<Node> nodeHolder = ThreadLocal.withInitial(Node::new);

	private final AtomicReference<Node> tail = new AtomicReference<>(newUnlockNode());

	public SimpleCLHLock() {
		super();
	}

	@Override
	public void lock() {
		Node node = nodeHolder.get();
		for (;;) {
			Node prev = node.prev;
			Node t = tail.get();
			// 1. prev 为 null 表示当前线程为入队, 此时尝试通过 CAS 入队到 CLH 队列尾部.
			if (prev == null) {
				node.setPrev(t);
				// 1.1 入队失败重置 prev.
				if (!tail.compareAndSet(t, node)) {
					node.setPrev(null);
				}
			}
			// 2. 已入队则轮询前一个节点状态.
			else if (prev.isLocked()) {
				Thread.yield();
			}
			// 3. 已获取到锁直接退出.
			else {
				break;
			}
 		}
	}

	@Override
	public void unlock() {
		Node node = nodeHolder.get();
		if (node.prev == null || node.prev.isLocked()) {
			throw new IllegalStateException("The thread does not hold a lock");
		}
		node.setPrev(null);
		node.setStatusUnlock();
		nodeHolder.remove();
	}

	private Node newUnlockNode() {
		Node node = new Node();
		node.locked = false;
		return node;
	}

	static class Node {

		volatile boolean locked = true;

		volatile Node prev;

		public boolean isLocked() {
			return this.locked;
		}

		public void setStatusUnlock() {
			this.locked = false;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}
}
