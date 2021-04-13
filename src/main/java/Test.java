import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Test {
	static {
		System.out.println("ddd");
	}
	public static void main(String[] args) {
		Runtime run = Runtime.getRuntime();
		run.addShutdownHook(new Thread(()->{System.out.println("虚拟机被关闭了");}));
		//关闭虚拟机, 也可以调用 System.exit(int status);
		System.exit(0);
		run.exit(0);
		/*
		 * BigDecimal big = new BigDecimal(10.0, new MathContext(1)); double b =
		 * 2.0 - 1.1; System.out.println(b);
		 */
	}
}

/**
 * 在 1 小时内每 10 秒钟蜂鸣一次
 * 
 * @author Doctor邓
 */
class BeeperControl {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public void beepForAnHour() {
		final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("beep");
			}
		};
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				/**
				 * cancel((boolean mayInterruptIfRunning)用法:
				 * 试图取消对此任务的执行。如果任务已完成、或已取消，或者由于某些其他原因而无法取消，则此尝试将失败。 当调用 cancel
				 * 时，如果调用成功，而此任务尚未启动，则此任务将永不运行。 如果任务已经启动，则 mayInterruptIfRunning
				 * 参数确定是否应该以试图停止任务的方式来中断执行此任务的线程。
				 */
				beeperHandle.cancel(true);
			}
		}, 60 * 60, SECONDS);
	}
}
