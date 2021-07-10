package concurrent;

public class TestSynchronized {
    static int i = 10000000;

    public static void main(String[] args) {
        // 创建成员内部类
        TestSynchronized.OutPut output = new TestSynchronized().new OutPut();

        new Thread() {
            public void run() {
                output.say();
            }
        }.start();

        new Thread() {
            public void run() {
                output.say();
            }
        }.start();

    }

    class OutPut {
        void say() {
            while (true) {
                //锁住整个对象
                synchronized (this) {
                    try {
                        System.out.println("i:" + i);
                        i--;
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //锁住方法
        synchronized void say1() {
            while (true) {
                try {
                    System.out.println("i:" + i);
                    i--;
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        void say2() {
            while (true) {
                //将锁住整个类
                synchronized (OutPut.class) {
                    try {
                        System.out.println("i:" + i);
                        i--;
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
