
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class Test {

	public static void main(String[] args) {
		BeeperControl bc = new BeeperControl();
		bc.beepForAnHour();
	}

}
/**
 * 在 1 小时内每 10 秒钟蜂鸣一次
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
				 * 试图取消对此任务的执行。如果任务已完成、或已取消，或者由于某些其他原因而无法取消，则此尝试将失败。
				 * 当调用 cancel 时，如果调用成功，而此任务尚未启动，则此任务将永不运行。
				 * 如果任务已经启动，则 mayInterruptIfRunning 参数确定是否应该以试图停止任务的方式来中断执行此任务的线程。 
				 */
				beeperHandle.cancel(true);
			}
		}, 60 * 60, SECONDS);
	}
}
