package gogogo.blockingQueue.stations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: caoyunji
 * @Date: 2020/5/20 1:14
 */
class BlockData2 {
    private volatile boolean flag = true;

//    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue blockingQueue = null;


    public BlockData2(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void produce() throws InterruptedException {
        int incrementAndGet;
        boolean offer;
        while (flag) {
//            incrementAndGet = atomicInteger.incrementAndGet();
            offer = blockingQueue.offer(1, 2, TimeUnit.SECONDS);
            if (offer) {
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t插入队列成功");
            } else {
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t插入队列失败");
            }
            Thread.sleep(1000);
        }
        System.out.println("停止生产");

    }

    public void comsue() throws InterruptedException {
        Object poll;
        while (true) {
            poll = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (Objects.isNull(poll)) {
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t消费失败");
                return;
            } else {
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t消费成功");
            }
            Thread.sleep(1000);
        }
    }

    public void stop() {
        this.flag = false;
    }

    private static String getLocaldateTimeNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
    }
}

public class BlockingQueueDemo2 {
    public static void main(String[] args) throws InterruptedException {
        BlockData2 blockData = new BlockData2(new ArrayBlockingQueue(2));
        new Thread(() -> {
            try {
                blockData.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadProduce").start();

        new Thread(() -> {
            try {
                blockData.comsue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadConsume").start();


        Thread.sleep(5000);
        System.out.println("要求停止！！");
        blockData.stop();


    }
}
