package concurrent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 练习 {@link java.util.concurrent.CompletionService}.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/8/11 20:15
 */
public class TestCompletionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestCompletionService.class);

    /**
     * 练习使用 {@link CompletionService} 实现执行多个任务只要一个任务正常完成就立即返回的类 Dubbo 的 Forking Cluster 模式.
     */
    private static <V> V invokeForkingCluster(List<Callable<V>> callables) {
        if (CollectionUtils.isEmpty(callables)) {
            throw new IllegalArgumentException("callables must not be null");
        }
        final ExecutorService executor = Executors.newFixedThreadPool(4);
        final BlockingQueue<Future<V>> queue = new LinkedBlockingQueue<>();
        final CompletionService<V> cs = new ExecutorCompletionService<>(executor, queue);
        final List<Future<V>> futures = new ArrayList<>(callables.size());

        for (Callable<V> callable: callables) {
            futures.add(cs.submit(callable));
        }
        try {
            // 循环从 CompletionService 中获取已完成的任务，当遇到第一个正常完成的任务时立即返回.
            for (int i = 1; i <= callables.size(); i++) {
                try {
                    return cs.take().get();
                } catch (InterruptedException e) {
                    throw new RuntimeException("task interrupt: ", e);
                } catch (ExecutionException e) {
                    // i >= 10 时即最后一个任务
                    if (i >=10 ) {
                        // 如果所有任务都执行出错则抛出异常.
                        //LOGGER.error("execution task error:", e.getCause());
                        throw new RuntimeException("execution task error:", e);
                    }
                    // 忽略任务执行的异常.
                }
            }
            // 不会发生.
            throw new RuntimeException("execution task error");
        } finally {
            // 如果有任务成功执行则取消其他任务.
            for(Future<V> f : futures) {
                f.cancel(true);
            }
            executor.shutdown();
        }
    }

    private static void invokeForkingClusterTest() {
        int size = 10;
        List<Callable<Long>> callables = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            callables.add(()->
            {
                int ran = RandomUtils.nextInt(0, 5);
                // 模拟任务随机出现异常.
                if (ran % 2 == 0) {
                    throw new RuntimeException("run error");
                }
                // 随机休眠一段时间，模拟执行业务操作.
                long result = ThreadUtils.sleepRandomSlience();
                System.out.println(result);
                // 将休眠时间作为模拟业务数据返回.
                return result;
            });
        }
        StopWatch watch = new StopWatch();
        watch.start();
        Long result = invokeForkingCluster(callables);
        watch.stop();
        LOGGER.info("execution result: {}, execute time: {}", result, watch.getTotalTimeMillis());
    }


    public static void main(String[] args) {
        invokeForkingClusterTest();
    }
}
