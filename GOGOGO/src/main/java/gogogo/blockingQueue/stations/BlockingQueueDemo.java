package gogogo.blockingQueue.stations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: caoyunji
 * @Date: 2020/5/20 1:14
 */
class BlockData {
    private int n = 0;
    private BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

    public void produce() {
        try {
            blockingQueue.put(1);
            System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t生产");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void comsue() {
        try {
            Thread.sleep(100);
            blockingQueue.take();
            System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t消费");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getLocaldateTimeNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
    }
}

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockData blockData = new BlockData();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                blockData.produce();
            }
        }, "ThreadAA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockData.comsue();
            }
        }, "ThreadBB").start();

    }
}
