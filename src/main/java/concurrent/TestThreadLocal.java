package concurrent;

public class TestThreadLocal {


    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                ThreadLocalData.getStudent().setName("邓华杰");
                ThreadLocalData.getStudent().setAge(22);
                System.out.println(Thread.currentThread().getName() + "放入数据：" + ThreadLocalData.getStudent().toString());
                new A().getData();
                new B().getData();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                ThreadLocalData.getStudent().setName("熊勇");
                ThreadLocalData.getStudent().setAge(24);
                System.out.println(Thread.currentThread().getName() + "放入数据：" + ThreadLocalData.getStudent().toString());
                new A().getData();
                new B().getData();


            }
        }).start();
    }

    static class A {
        public void getData() {
            System.out.println("A 从" + Thread.currentThread().getName() + "线程，取出数据：" + ThreadLocalData.getStudent());
        }
    }

    static class B {
        public void getData() {
            System.out.println("B 从" + Thread.currentThread().getName() + "线程，取出数据：" + ThreadLocalData.getStudent());
        }
    }
}

class ThreadLocalData {
    private static ThreadLocal<ThreadLocalData> map = new ThreadLocal<ThreadLocalData>();

    private ThreadLocalData() {
    }

    public static ThreadLocalData getStudent() {
        ThreadLocalData stu = map.get();
        if (null == stu) {
            stu = new ThreadLocalData();
            map.set(stu);
        }

        return stu;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }
}