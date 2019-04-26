package designPattern;

public class Singleton {
    // volatile static Singleton instance; //声明成 volatile
    private static Singleton instance; //声明成 volatile
    private Singleton (){}
    public static Singleton getSingleton() {
        if (instance == null) {                         
            synchronized (Singleton.class) {
                if (instance == null) {       
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Singleton.getSingleton());
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Singleton.getSingleton());
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Singleton.getSingleton());
            }
        };
        Runnable runnable4 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Singleton.getSingleton());
            }
        };
        Runnable runnable5 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Singleton.getSingleton());
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);
        Thread thread4 = new Thread(runnable4);
        Thread thread5 = new Thread(runnable5);
        thread1.run();
        thread2.run();
        thread3.run();
        thread4.run();
        thread5.run();
    }
}