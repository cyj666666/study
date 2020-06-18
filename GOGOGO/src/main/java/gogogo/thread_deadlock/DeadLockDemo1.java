package gogogo.thread_deadlock;

import gogogo.util.MyUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: caoyunji
 * @Date: 2020/5/21 14:37
 */
class DeadLockSource {


}

public class DeadLockDemo1 {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();


        new Thread(() -> {
            lock1.lock();
            MyUtils.recordLog("获得lock1");
            lock2.lock();
            MyUtils.recordLog("获得lock2");
        }, "A").start();

        new Thread(() -> {
            lock2.lock();
            MyUtils.recordLog("获得lock2");
            lock1.lock();
            MyUtils.recordLog("获得lock1");
        }, "B").start();


    }

}
