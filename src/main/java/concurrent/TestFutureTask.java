package concurrent;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 练习 {@link java.util.concurrent.FutureTask}.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/8/4 20:24
 */
public class TestFutureTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestFutureTask.class);

    private static void useByTask() {
        FutureTask<Integer> task = new FutureTask<>(()->{
            LOGGER.info("休眠时长：{}", ThreadUtils.sleepRandomSlience());
            return RandomUtils.nextInt(0, 100);
        });
        // 将 FutureTask 提交给 ExecutorService 执行.
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(task);
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            // 通过 FutureTask 同步阻塞等待结果返回.
            task.get();
            stopWatch.stop();
            LOGGER.info("等待总时间（秒）：{}", stopWatch.getTotalTimeSeconds());
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("use FutureTask as a Future error:", e);
        } finally {
            executorService.shutdown();
        }
    }

    private static void useByRunnable() {
        FutureTask<Integer> task = new FutureTask<>(()->{
            ThreadUtils.sleepRandomSlience();
            return RandomUtils.nextInt(0, 100);
        });
        // 单独创建线程执行 FutureTask.
        new Thread(task).start();
        try {
            // 通过 FutureTask 同步阻塞获取执行结果.
            int result = task.get();
            LOGGER.info("The result of a FutureTask executed through a thread is  {}", result);
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("use FutureTask as a Runnable error:", e);
        }
    }

    /**
     * 泡茶, 示意图见 <img src="https://static001.geekbang.org/resource/image/9c/8e/9cf7d188af9119a5e76788466b453d8e.png">.
     * <p>
     * 用两个线程 T1 和 T2 来完成烧水泡茶程序，T1 负责洗水壶、烧开水、泡茶这三道工序.
     * T2 负责洗茶壶、洗茶杯、拿茶叶三道工序，其中 T1 在执行泡茶这道工序时需要等待 T2 完成拿茶叶的工序.
     * </p>
     */
    private static void makeTea() {
        /**
         * T1Task 需要执行的任务：洗水壶、烧开水、泡茶.
         */
        class T1Task implements Callable<String> {
            // T1 任务需要 T2 任务的结果.
            private Future<String> t2Future;

            T1Task(Future<String> t2Future) {
                this.t2Future = t2Future;
            }

            @Override
            public String call() throws Exception {
                LOGGER.info("T1:洗水壶...");
                TimeUnit.SECONDS.sleep(1);

                LOGGER.info("T1:烧开水...");
                TimeUnit.SECONDS.sleep(15);

                // 获取 T2 任务执行结果开始泡茶.
                String tea = t2Future.get();
                LOGGER.info("T1:拿到茶叶:{}", tea);

                LOGGER.info("T1:泡茶");
                return "上茶:" + tea;
            }
        }
        /**
         * T2Task 需要执行的任务：洗茶壶、洗茶杯、拿茶叶.
         */
        class T2Task implements Callable<String> {

            @Override
            public String call() throws Exception {
                LOGGER.info("T2:洗茶壶...");
                TimeUnit.SECONDS.sleep(1);

                LOGGER.info("T2:洗茶杯...");
                TimeUnit.SECONDS.sleep(2);

                LOGGER.info("T2:拿茶叶...");
                TimeUnit.SECONDS.sleep(1);

                return "龙井";
            }
        }

        FutureTask<String> t2Future = new FutureTask<>(new T2Task());
        FutureTask<String> t1Future = new FutureTask<>(new T1Task(t2Future));

        // 创建两个线程分别执行泡茶相关任务.
        new Thread(t1Future).start();
        new Thread(t2Future).start();

        try {
            // 同步阻塞等待泡茶完成.
            String tea = t1Future.get();
            LOGGER.info(tea);
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Tea making error through FutureTask:", e);
        }

    }

    public static void main(String[] args) {
        useByTask();
        useByRunnable();
        makeTea();
    }

}
