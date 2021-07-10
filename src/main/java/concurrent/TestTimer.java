package concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {

    public static void main(String[] args) {
        //Timer 是定时器, TimerTask 是具体的任务
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                //System.out.println("开始一波");
            }

            //后两个参数：前一个代表首次执行延迟(必须)，后一个代表循环执行每次间隔时间(不是必须)
        }, 10000, 3000);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("开始前奏 ");

                new Timer().schedule(new TimerTask() {

                    @Override
                    public void run() {
                        System.out.println("开始尾声");
                    }
                }, 3000);
            }

        }, 2000, 5000);

        while (true) {
            System.out.println(new Date().getSeconds());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
