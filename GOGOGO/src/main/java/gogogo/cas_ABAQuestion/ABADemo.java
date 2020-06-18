package gogogo.cas_ABAQuestion;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: caoyunji
 * @Date: 2020/5/18 16:00
 * 带时间戳的原子引用
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
//        ======================ABA问题的产生=================
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "T1").start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 102) + "\t" + atomicReference.get());
        }, "T2").start();

        //        ======================ABA问题的解决=================
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        }, "T3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 1002, stamp, stamp + 1) + "\t" + atomicStampedReference.getReference());
        }, "T4").start();

    }
}
