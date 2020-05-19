package gogogo.阻塞队列.api操作;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: caoyunji
 * @Date: 2020/5/19 18:57
 */
public class BlockingQueueDemmo1 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        BlockingQueue syncQueue = new SynchronousQueue();

        syncQueue(syncQueue);//定制同步队列


//        putAndTake(blockingQueue);
//        offerAndPoll(blockingQueue);
//        exceptionType(blockingQueue);

    }

    private static void syncQueue(BlockingQueue syncQueue) {
        new Thread(() -> {
            try {
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\tput 1");
                syncQueue.put(1);

                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\tput 2");
                syncQueue.put(2);

                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\tput 3");
                syncQueue.put(3);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread AA").start();

        new Thread(() -> {
            try {
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\ttake\t" + syncQueue.take());
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\ttake\t" + syncQueue.take());
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\ttake\t" + syncQueue.take());


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread BB").start();
    }

    private static String getLocaldateTimeNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

    private static void putAndTake(BlockingQueue blockingQueue) throws InterruptedException {
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("c");


        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    private static void offerAndPoll(BlockingQueue blockingQueue) {
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println("element:" + blockingQueue.element());
        System.out.println("peek:" + blockingQueue.peek());


        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void exceptionType(BlockingQueue blockingQueue) {
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }


}
