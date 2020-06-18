package gogogo.blockingQueue.stations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: caoyunji
 * @Date: 2020/5/19 22:01
 */

/**
 * 有东西不能生产，没东西不能消费
 */
class DataSource {
    private int n = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();//实现条件的等待唤醒

    public void inCrement() throws InterruptedException {
        lock.lock();
        try {
            while (n != 0) {
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t存在商品，无法生产,n=" + n);
                condition.await();//作用是阻塞等待，类似Thread.sleep()
            }
            Thread.sleep(1000);
            n++;
            System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t生产者+1,n=" + n);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void deCrement() throws InterruptedException {
        lock.lock();
        try {
            while (n == 0) {
                System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t商品为空，无法消费,n=" + n);
                condition.await();
            }
            Thread.sleep(1000);
            n--;
            System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t消费者-1,n=" + n);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    private static String getLocaldateTimeNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }


}

public class TraditionalDemo {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    dataSource.inCrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    dataSource.deCrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    dataSource.deCrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "CCC").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    dataSource.deCrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "DDD").start();

    }


}
