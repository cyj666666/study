package gogogo.coundownLatch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: caoyunji
 * @Date: 2020/5/19 15:29
 */
public class coundownLatchDemo1 {

    public static void main(String[] args) {
//        countryTogether();
        closeDoor();
    }

    private static void countryTogether() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t完成任务");
                countDownLatch.countDown();
            }, CountryEnum.getEnum(i + 1)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);
        System.out.println(getLocaldateTimeNow() + "\t秦国\t统一天下");
    }

    private static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t完成任务");
                countDownLatch.countDown();
//                System.out.println(countDownLatch.getCount());
            }, "Thread" + i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t 关门");
    }


    private static String getLocaldateTimeNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
    }


}
