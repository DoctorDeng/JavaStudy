package concurrent.kongzhongwang;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//不能改动此Test类	
public class Test3 extends Thread {
    private TestDo2 testDo;
    private String key;
    private String value;
    public static CountDownLatch order = new CountDownLatch(3);
    public static boolean isWait = false;
    public static List<String> list = new ArrayList<>();
    private static Lock lock = new ReentrantLock();

    public Test3(String key, String key2, String value) {
        this.testDo = TestDo2.getInstance();
			/*常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
			以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
        this.key = key + key2;
        this.value = value;
    }

    public static void main(String[] args) throws InterruptedException {
        Test3 a = new Test3("1", "", "1");
        Test3 b = new Test3("1", "", "2");
        Test3 c = new Test3("3", "", "3");
        Test3 d = new Test3("4", "", "4");
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        a.start();
        b.start();
        c.start();
        d.start();
    }

    public void run() {
        lock.lock();
        if (!list.contains(key)) {
            list.add(key);
            lock.unlock();
        } else {
            if (!isWait) {
                try {
                    isWait = true;
                    lock.unlock();
                    order.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        testDo.doSome(key, value);
        order.countDown();
    }
}

class TestDo2 {
    private TestDo2() {
    }

    private static TestDo2 _instance = new TestDo2();

    public static TestDo2 getInstance() {
        return _instance;
    }

    public void doSome(Object key, String value) {
        // 以大括号内的是需要局部同步的代码，不能改动!
        {
            try {
                Thread.sleep(1000);
                System.out.println(key + ":" + value + ":"
                        + (System.currentTimeMillis() / 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}