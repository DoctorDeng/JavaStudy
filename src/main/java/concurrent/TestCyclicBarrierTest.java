package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 练习 CyclicBarrier
 *
 * @author Doctor邓
 */
public class TestCyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CyclicBarrier cb = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            Runnable runnable = () -> {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "已到达集合地点1, 当前已有" + (cb.getNumberWaiting() + 1));

                    cb.await();

                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "到达集合地点2, 当前已有" + (cb.getNumberWaiting() + 1));

                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "已到达集合地点3, 当前已有" + (cb.getNumberWaiting() + 1));

                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            };
            service.execute(runnable);
        }
    }
}
