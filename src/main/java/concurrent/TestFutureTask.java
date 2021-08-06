package concurrent;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

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

    public static void main(String[] args) {
        useByTask();
        useByRunnable();
    }

}
