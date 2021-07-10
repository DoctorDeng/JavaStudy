package concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 练习 Callable 和 Future 的使用, 让 线程执行返回结果
 *
 * @author Doctor邓
 */
public class TestCallableAndFuture {
    /**
     * 练习单个带返回值的任务, 并对返回值进行处理
     */
    public static void testCallFuture() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        /*
         * 一般情况下: 使用 submit() 方法提交具有返回值的任务, execute() 提交没有返回值的任务
         */
        Future<String> future = threadPool.submit(new Callable<String>() {
            public String call() throws Exception {
                Thread.currentThread();
                Thread.sleep(10000);
                return "hello";
            }

            ;
        });

        System.out.println("----等待结果----");
        try {
            //当任务执行完成, 返回结果后才会继续执行下面的语句
            System.out.println("拿到结果：" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 练习批量处理带返回值的任务
     */
    public static void testCallFutures() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
        //批量添加带返回值的任务
        for (int i = 0; i < 10; i++) {
            final int task = i;
            Callable<Integer> callable = () -> {
                Thread.sleep(new Random().nextInt(5) * 1000);
                return task;
            };
            completionService.submit(callable);
        }
        //处理所有带返回值的任务返回的值
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {


    }
}
