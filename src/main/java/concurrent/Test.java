package concurrent;

public class Test {

    public synchronized void say() {
        System.out.println("1");
        nosay();
    }

    public synchronized void nosay() {
        System.out.println("2");
    }

    public static void main(String[] args) {

        Test test = new Test();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    test.say();
                    try {
                        Thread.currentThread();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
