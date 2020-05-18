package gogogo.独占锁共享锁互斥锁;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: caoyunji
 * @Date: 2020/5/18 23:58
 */
class MyCache2 {  //资源类
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t开始写入数据\t" + key);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t结束写入数据\t" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t开始读取数据，结果为：\t" + map.get(key));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t结束读取数据，结果为：\t" + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    private String getLocaldateTimeNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }
}


public class ReentrantReadWriteLockDemo2 {

    public static void main(String[] args) {
        MyCache2 myCache2 = new MyCache2();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache2.put(finalI + "", finalI + "");
            }, "Thread" + i).start();
        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache2.get(finalI + "");
            }, "Thread" + i).start();
        }

    }
}
