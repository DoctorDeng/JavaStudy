package  concurrent;


public class Add10K {
    private long count = 0;
    private void add10K() {
        int idx = 0;
        while(idx++ < 100000000) {
            count += 1;
        }
    }
    public  static long calc() throws InterruptedException {
        final Add10K add10K = new Add10K();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(()->{
            add10K.add10K();
        });
        Thread th2 = new Thread(()->{
            add10K.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return add10K.count;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calc());
    }
}