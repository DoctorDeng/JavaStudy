package concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 练习使用 Exchange 进行两个线程之间的数据交换
 *
 * @author Doctor邓
 */
public class TestExchange {

    public static void testExchanege() {
        ExecutorService service = Executors.newCachedThreadPool();
        Exchanger<String> exchanger = new Exchanger<>();

        service.execute(() -> {
            try {
                String data1 = "邓博士";
                System.out.println("线程" + Thread.currentThread().getName() +
                        "正把数据" + data1 + "换出去");

                Thread.sleep((long) (Math.random() * 10000));

                String data2 = exchanger.exchange(data1);
                System.out.println("线程" + Thread.currentThread().getName() +
                        "换回的数据为" + data2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.execute(() -> {
            try {
                String data2 = "doctordeng";
                System.out.println("线程" + Thread.currentThread().getName() +
                        "正把数据" + data2 + "换出去");

                Thread.sleep((long) (Math.random() * 10000));

                String data1 = exchanger.exchange(data2);
                System.out.println("线程" + Thread.currentThread().getName() +
                        "换回的数据为" + data1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        testExchanege();
    }
}
