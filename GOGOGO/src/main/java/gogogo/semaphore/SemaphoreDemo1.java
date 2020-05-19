package gogogo.semaphore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

/**
 * @Author: caoyunji
 * @Date: 2020/5/19 18:35
 */
public class SemaphoreDemo1 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t抢占成功");

                    Thread.sleep(3000);
                    semaphore.release();
                    System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t释放成功");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }, "Thread" + i).start();
        }


    }

    private static String getLocaldateTimeNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }


}
