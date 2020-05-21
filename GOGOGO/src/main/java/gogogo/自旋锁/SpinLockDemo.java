package gogogo.自旋锁;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: caoyunji
 * @Date: 2020/5/18 22:04
 */
public class SpinLockDemo {
    AtomicReference<Integer> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread currentThread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, 6)) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程：\t" + currentThread.getName() + "\t 获取锁失败，再次尝试获取。。。。");
        }
        System.out.println("当前线程：\t" + currentThread.getName() + "\t 获得了锁");
    }

    public void myUnlock() {
        Thread currentThread = Thread.currentThread();
        atomicReference.compareAndSet(6, null);
        System.out.println("当前线程：\t" + currentThread.getName() + "\t 解除锁");
    }


    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "T1").start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myLock();

        }, "T2").start();

    }


}
