package gogogo.reEntranLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: caoyunji
 * @Date: 2020/5/18 23:58
 */
class MyCache {  //资源类
    private volatile Map<String, Object> map = new HashMap<>();
    private Lock lock = new ReentrantLock();

    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "\t开始写入数据\t" + key);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t结束写入数据\t" + key);
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "\t开始读取数据，结果为：\t" + map.get(key));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t结束读取数据，结果为：\t" + result);
    }


}


public class ReentrantReadWriteLockDemo1 {

    public static void main(String[] args) {
        MyCache2 myCache = new MyCache2();

        new Thread(() -> {
            myCache.get("1");
        }, "T1").start();

        new Thread(() -> {
            myCache.put("1", 1);
        }, "T2").start();
    }
}
