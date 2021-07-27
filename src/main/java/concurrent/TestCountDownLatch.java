package concurrent;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 练习 {@link CountDownLatch}.
 *
 * @author Doctor邓
 */
public class TestCountDownLatch {
    private static final Logger log = LoggerFactory.getLogger(TestCountDownLatch.class);

    private static void twoCountDownLatchExample() {
        class Worker implements Runnable {
            private final CountDownLatch startSignal;
            private final CountDownLatch doneSignale;
            public Worker(CountDownLatch startSignal, CountDownLatch doneSignale) {
                this.startSignal = startSignal;
                this.doneSignale = doneSignale;
            }
            @Override
            public void run() {
                try {
                    startSignal.await();
                    log.info("thread {} is doing something", Thread.currentThread().getName());
                    Thread.sleep(RandomUtils.nextInt(1000, 3000));
                } catch (InterruptedException e) {
                    // noting to do
                } finally {
                    doneSignale.countDown();
                }
            }
        }
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal  = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker(startSignal, doneSignal));
        }

        while (true) {
            System.out.println("输入 1 开始");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if ("1".equals(input)) {
                startSignal.countDown();
                break;
            }
        }

        try {
            doneSignal.await();
            log.info("所有 worker 执行完成");
        } catch (InterruptedException e) {
            // noting to do
        } finally {
            executorService.shutdown();
        }
    }

    private static void oneCountDownLatchExample() {
        class Worker implements Runnable {
            private final CountDownLatch countDownLatch;
            private final Vector<Long> ids;

            public Worker(CountDownLatch countDownLatch, Vector<Long> ids) {
                this.countDownLatch = countDownLatch;
                this.ids = ids;
            }
            @Override
            public void run() {
                try {
                    log.info("thread {} is doing something", Thread.currentThread().getName());
                    Thread.sleep(RandomUtils.nextInt(1000, 3000));
                    ids.add(Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    // noting to do
                } finally {
                    countDownLatch.countDown();
                }
            }
        }
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Vector<Long> ids = new Vector<>();
        for (int i = 0; i< 5; i++) {
            new Thread(new Worker(countDownLatch, ids)).start();
        }

        try {
            countDownLatch.await();
            System.out.println("所有 Worker 任务完成, 结果为:" + ids);
        } catch (InterruptedException e) {
            // noting to do
        }

    }


    public static void main(String[] args) {
        oneCountDownLatchExample();
        //twoCountDownLatchExample();
    }
}
