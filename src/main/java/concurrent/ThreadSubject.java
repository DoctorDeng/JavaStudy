package concurrent;

/**
 * 多线程题目代码, 题目如下: 子线程循环 10 次, 接着主线程循环 100 次, 接着又回到子线程 循环 10 次, 接着再回到主线程又循环 100
 * 次, 如此循环 50次, 请写出程序
 *
 * @author Doctor邓
 */
public class ThreadSubject {
    /**
     * 表示主线程和子线程是否在运行的变量, true 为子线程运行, false 为主线程执行
     */
    private volatile boolean isChildRun = true;

    public synchronized void mainThreadMethod() {
        // 这里不采用  while (!isChildRun) { for ().....} 的原因是
        // 在 主线程判断无权进入循环后, 最后被唤醒时不会执行循环代码, 而是再次执行 mainThreadMethod()方法, 这样就无法控制循环的次数了
        // 使用 wait() 方法就可以释放资源, 让子线程进入循环
        // 采用 while 来判断 而不用 if ，防止其他线程(不是测试的子线程)使用 notify() 或 notifyAll() 唤醒主线程时,而这个时候主线程
        // 没有进入循环权限, 使用 if 后就不会再次判断是否由权进入循环, 而是继续往下执行循环代码, 这样就发生了 BUG, 使用 while() 就能够
        // 再次判断主线程是否有权进入循环, 避免了因为不相干的线程唤醒主线程, 而主线程没有进入循环权限, 主线程却执行循环代码的 BUG
        while (isChildRun) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= 100; i++) {
            System.out.println("主线程第" + i + "次循环");
        }

        isChildRun = true;
        //唤醒一个等待的线程
        this.notify();
    }

    public synchronized void childThreadMethod() {
        while (!isChildRun) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= 10; i++) {
            System.out.println("子线程第" + i + "次循环");
        }

        isChildRun = false;
        this.notify();
    }

    public static void main(String[] args) {
        ThreadSubject threadSubject = new ThreadSubject();

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    threadSubject.childThreadMethod();
                }
            }
        }).start();

        for (int i = 1; i <= 50; i++) {
            threadSubject.mainThreadMethod();
        }
    }
}
