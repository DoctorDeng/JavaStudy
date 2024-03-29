package concurrent;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 练习 {@link CyclicBarrier}.
 *
 * @author doctordeng
 */
public class TestCyclicBarrier {
    private static final Logger log = LoggerFactory.getLogger(TestCountDownLatch.class);

    private static void cyclicBarrierTest() {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        final Queue<Long> orderQueue    = new ArrayBlockingQueue<>(1);
        final Queue<Long> deliveryQueue = new ArrayBlockingQueue<>(1);

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            // 回调 Runnable 此处为同步执行, 建议使用线程池异步执行.
            Long order = orderQueue.poll();
            Long delivery = deliveryQueue.poll();
            if (order != null && delivery != null) {
                log.info("开始对账, 订单 id {}, 派送单 id {}", order, delivery);
                ThreadUtils.sleepSlience(3000);
            }
        });

        executorService.execute(()->{
            try {
                while (true) {
                    ThreadUtils.sleepRandomSlience();
                    Long order = RandomUtils.nextLong(0, 100000);
                    orderQueue.add(order);
                    log.info("查询订单, 订单 id：{}", order);
                    cyclicBarrier.await();
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                // noting to do
            }
        });
        executorService.execute(()->{
            try {
                while (true) {
                    ThreadUtils.sleepRandomSlience();
                    Long delivery = RandomUtils.nextLong(0, 100000);
                    deliveryQueue.add(delivery);
                    log.info("查询派送单, 派送单 id：{}", delivery);
                    cyclicBarrier.await();
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                // noting to do
            }
        });

    }

    public static void main(String[] args) {
        cyclicBarrierTest();
    }
}
