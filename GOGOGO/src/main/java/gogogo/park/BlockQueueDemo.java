package gogogo.park;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Author: caoyunji
 * @Date: 2020/5/22 21:02
 */
public class BlockQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        String[] arr = new String[]{"A", "B"};

        new Thread(() -> {
            while (true) {
//                for(String str:arr){
                try {
                    blockingQueue.put("A");
                    blockingQueue.put("B");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                }


            }

        }, "A").start();

        new Thread(() -> {
            while (true) {
                String poll = null;
                try {
                    poll = blockingQueue.take();
                    System.out.println(poll);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "B").start();


    }


}
