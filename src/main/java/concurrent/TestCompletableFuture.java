package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * 练习 {@link CompletableFuture}.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/8/7 13:56
 */
public class TestCompletableFuture {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestCompletableFuture.class);

    /**
     * 练习使用 {@link CompletableFuture} 完成具有串行关系的任务.
     */
    private static void serialRelation() {
        CompletableFuture<String> f0 = CompletableFuture.supplyAsync(() -> "Hello World")
                .thenApply(s -> s + " QQ")
                .thenApply(String::toUpperCase)
                // thenApply、thenAccept、thenRun 都只是在完成当前阶段后执行给定的操作,
                // 而 thenCompose 方法由于给定操作返回值是 CompletableFuture，因此在执行完成给定操作后还会执行返回的 CompletableFuture
                // 也就是说 thenCompose 方法会创建一个子流程.
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " and sleep" + ThreadUtils.sleepRandomSlience()));
        LOGGER.info(f0.join());
    }

    /**
     * 练习使用 {@link CompletableFuture} 完成具有 AND 汇聚关系的任务.
     */
    private static void andConvergentRelation() {
        CompletableFuture<Long> f1 = CompletableFuture.supplyAsync(() -> {
            long sleep = ThreadUtils.sleepRandomSlience();
            LOGGER.info("f1 sleep {}", sleep);
            return sleep;
        });
        CompletableFuture<Long> f2 = CompletableFuture.supplyAsync(() -> {
            long sleep = ThreadUtils.sleepRandomSlience();
            LOGGER.info("f2 sleep {}", sleep);
            return sleep;
        });
        // f3 等待  f2 和 f1 都执行完成后计算两者休眠时间综合.
        CompletableFuture<Long> f3 = CompletableFuture.supplyAsync(() -> f2.thenCombine(f1, Long::sum).join());
        LOGGER.info("sleep total time: {}", f3.join());
    }

    /**
     * 练习使用 {@link CompletableFuture} 完成具有 OR 汇聚关系的任务.
     */
    private static void orCovergentRalation() {
        CompletableFuture<Long> f1 = CompletableFuture.supplyAsync(() -> {
           long sleep = ThreadUtils.sleepRandomSlience();
           LOGGER.info("f1 sleep: {}", sleep);
           return sleep;
        });
        CompletableFuture<Long> f2 = CompletableFuture.supplyAsync(() -> {
            long sleep = ThreadUtils.sleepRandomSlience();
            LOGGER.info("f2 sleep: {}", sleep);
            return sleep;
        });

        long sleep = f2.applyToEither(f1, s -> s).join();
        LOGGER.info("sleep total time: {}", sleep);
    }

    private static void noExceptionHandler() {
        CompletableFuture<Integer> f0 = CompletableFuture.supplyAsync(() -> 7/0).thenApply(r -> r*10);
        LOGGER.info("The calculation result is: {}", f0.join());
    }

    /**
     * 练习使用 {@link CompletableFuture} 时对出现异常的任务进行处理.
     */
    private static void withExceptionHandler() {
        CompletableFuture<Integer> f0 = CompletableFuture.supplyAsync(() -> 7/0)
                // 对异常进行处理.
                .exceptionally(throwable -> {
                    // 注意：导致异常发生的原始异常在 throwable 的 getCause() 中.
                    if (throwable.getCause() instanceof ArithmeticException) {
                        LOGGER.error("divisor cannot be 0, error message is: ", throwable);
                    }
                    return 0;
                })
                // 作用类似于 try/finally 中的 finally.
                // 无论是否发生异常都会被执行，与之相似的方法还有 handle().
                .whenComplete((integer, throwable) -> {
                    if (integer != null) {
                        LOGGER.info("Intermediate calculation result is: {}", integer);
                    }
                    // 由于异常已经被处理, 因此 throwable 会为 null.
                    if (throwable != null) {
                        LOGGER.info("An error occurred in the calculation");
                    }
                }).thenApply(r -> r*10);
        LOGGER.info("Final calculation result is: {}", f0.join());
    }

    public static void main(String[] args) {
//        serialRelation();
//        andConvergentRelation();
//        orCovergentRalation();
//        noExceptionHandler();
        withExceptionHandler();
    }
}
