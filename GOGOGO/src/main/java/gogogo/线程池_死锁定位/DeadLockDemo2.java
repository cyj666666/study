package gogogo.线程池_死锁定位;

import gogogo.util.MyUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: caoyunji
 * @Date: 2020/5/21 14:37
 */
class DeadLockSource2 implements Runnable {
    private String lock1;
    private String lock2;

    public DeadLockSource2(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            MyUtils.recordLog("已获取" + lock1 + "，尝试获取" + lock2);
            synchronized (lock2) {
                MyUtils.recordLog("已获取" + lock2 + "，尝试获取" + lock1);
            }
        }
    }
}

public class DeadLockDemo2 {
    public static void main(String[] args) {
        String lock1 = "lock1";
        String lock2 = "lock2";
        DeadLockSource2 source1 = new DeadLockSource2(lock1, lock2);
        DeadLockSource2 source2 = new DeadLockSource2(lock2, lock1);
        new Thread(source1, "A").start();
        new Thread(source2, "B").start();
    }

}
