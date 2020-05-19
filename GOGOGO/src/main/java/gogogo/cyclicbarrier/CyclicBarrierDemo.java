package gogogo.cyclicbarrier;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: caoyunji
 * @Date: 2020/5/19 17:15
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐了，可以执行最后一个线程了！！！");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t开始执行\t" + cyclicBarrier.getNumberWaiting());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
//                if (Objects.equals(Thread.currentThread().getName(), "3")) {
//                    throw new RuntimeException("1111111");
//                }
//                System.out.println(Thread.currentThread().getName() + "\t执行完成");

            }, "Thread" + i).start();

        }

    }


}


