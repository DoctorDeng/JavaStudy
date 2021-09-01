package concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 练习 Lock
 *
 * @author doctordeng
 */
public class TestLock {

    public static void main(String[] args) {
        // 创建成员内部类
        TestLock.Outputer output = new TestLock().new Outputer();
        String name = "山东快放假了可是对方离开交水电费";
        new Thread() {
            public void run() {
                output.output(name);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                output.output(name);

            }
        }.start();

    }

    class Outputer {
        // 创建一把锁, 这里为 重入锁
        Lock lock = new ReentrantLock();

        public void output(String name) {
            // 加锁
            lock.lock();
            /**
             * 使用 try{}finally{} 是防止运行的代码抛出异常而锁没有被释放的问题
             */
            try {
                for (int i = 0, len = name.length(); i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                // 解锁
                lock.unlock();
            }
        }
    }
}
