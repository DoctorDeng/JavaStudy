package concurrent;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;


public class SimpleArrayBlockingQueueTest {

    @Test
    public void enqueue() throws InterruptedException {
        final SimpleArrayBlockingQueue<String> queue = new SimpleArrayBlockingQueue<>(10);
        Runnable test = () -> {
            for (int i = 0; i < 15; i++) {
                long id = Thread.currentThread().getId();
                try {
                    System.out.println("Thread " + id + " 入队：" + i);
                    queue.enqueue(id + "_" + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread = new Thread(test);
        thread.start();

        Thread.sleep(1000);
        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(100);
                System.out.println("出队：" + queue.dequeue());
                System.out.println(queue.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}