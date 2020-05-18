package gogogo.公平锁非公平锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: caoyunji
 * @Date: 2020/5/18 19:09
 */
class Phone implements Runnable {


    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t sendEmail");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        reEntran1();
    }

    public void reEntran1() {
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t reEntran1");
            reEntran2();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void reEntran2() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t reEntran2");
        } finally {
            lock.unlock();
        }
    }
}


public class ReentranSynchronizedDemo1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

//        new Thread(() -> {
//            phone.sendEmail();
//        }, "T2").start();
//        new Thread(() -> {
//            phone.sendSMS();
//        }, "T1").start();
        new Thread(phone, "T3").start();
        new Thread(phone, "T4").start();


    }


}
