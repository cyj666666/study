package gogogo.SpinLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: caoyunji
 * @Date: 2020/5/18 22:04
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(Thread publicThread) {
        Thread currentThread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, publicThread)) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程：\t" + currentThread.getName() + "\t 获取锁失败，再次尝试获取。。。。");
        }
        System.out.println("当前线程：\t" + currentThread.getName() + "\t 获得了锁");
    }

    public void myUnlock(Thread publicThread) {
        Thread currentThread = Thread.currentThread();
        atomicReference.compareAndSet(publicThread, null);
        System.out.println("当前线程：\t" + currentThread.getName() + "\t 解除锁");
    }


    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        Thread mainThread = Thread.currentThread();
        new Thread(() -> {
            spinLockDemo.myLock(mainThread);
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock(mainThread);
        }, "T1").start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myLock(mainThread);

        }, "T2").start();

    }


}
