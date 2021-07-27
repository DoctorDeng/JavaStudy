package concurrent;

import org.apache.commons.lang3.RandomUtils;

/**
 * 线程工具类.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/7/27 14:55
 */
public abstract class ThreadUtils {


    public static void sleepSlience(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // ignore interrupt
        }
    }

    public static void sleepRandomSlience() {
        sleepSlience(RandomUtils.nextLong(1000, 3000));
    }
}
