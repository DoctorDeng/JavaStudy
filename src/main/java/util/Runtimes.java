package util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 运行时工具类.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/12/28 12:14
 */
public abstract class Runtimes {

	private Runtimes() {
	}

	/**
	 * 是否正在退出.
	 */
	private static final AtomicBoolean exiting = new AtomicBoolean(false);

	/**
	 * 在新的线程中终止当前 Java 虚拟机, 不会阻塞当前线程.
	 * @param status 退出状态码, 0 表示正常退出, 其他表示非正常退出
	 * @param softExitDuration 软退出限制时间, 单位秒. 超过该时间限制后将调用 {@link Runtime#halt(int)} 强制退出虚拟机
	 * @see #exit(int, int)
	 */
	public static void exitAsync(int status, int softExitDuration) {
		Thread thread = new Thread(() -> exit(status, softExitDuration));
		thread.setContextClassLoader(Runtimes.class.getClassLoader());
		thread.start();
	}

	/**
	 * 终止当前 Java 虚拟机, 相比于 {@link System#exit(int)} 方法, 该方法会首先调用 {@link Runtime#exit(int)} 按照正常流程退出 JVM，
	 * 如果指定时间后 JVM 依然未退出(一般是 shutdown hook 发生死锁、死循环等问题导致一直阻塞无法执行完成)则调用 {@link Runtime#halt(int)} 方法强制退出 JVM.
	 * @param status 退出状态码, 0 表示正常退出, 其他表示非正常退出
	 * @param softExitDuration 软退出限制时间, 单位秒. 超过该时间限制后将调用 {@link Runtime#halt(int)} 强制退出虚拟机
	 */
	public static void exit(int status, int softExitDuration) {
		if (!exiting.compareAndSet(false, true)) {
			return;
		}
		if (softExitDuration <= 0) {
			throw new IllegalArgumentException("softExitDuration must be greater than 0");
		}
		// 强制退出时间, 超过该时间将调用 Runtime#halt() 方法强制退出 JVM.
		long forcedExitTime = System.currentTimeMillis() + softExitDuration * 1000L;
		Thread exitWatchdog = new Thread(()-> {
			while (true) {
				long time = System.currentTimeMillis();
				if (time < forcedExitTime) {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException ingore) {
						// ignore.
					}
				} else {
					Runtime.getRuntime().halt(status);
				}
			}
		});
		exitWatchdog.start();
		System.exit(status);
	}

}
