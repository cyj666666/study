package gogogo.blockingQueue.stations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: caoyunji
 * @Date: 2020/5/20 0:26
 */
class Source {
    private String threadName = "A";
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print(String threadName) {
        lock.lock();
        try {
            while (!Objects.equals(threadName, this.threadName)) {
                getCurrentCondition(threadName).await();
            }
            Thread.sleep(1000);
            System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t完成任务");
            this.threadName = getNextThreadName(threadName);
            getNextCondition(threadName).signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private Condition getCurrentCondition(String threadName) {
        switch (threadName) {
            case "A":
                return condition1;
            case "B":
                return condition2;
            case "C":
                return condition3;
        }
        return null;
    }

    private Condition getNextCondition(String threadName) {
        switch (threadName) {
            case "A":
                return condition2;
            case "B":
                return condition3;
            case "C":
                return condition1;
        }
        return null;
    }

    private String getNextThreadName(String threadName) {
        switch (threadName) {
            case "A":
                return "B";
            case "B":
                return "C";
            case "C":
                return "A";
        }
        return null;
    }


    private static String getLocaldateTimeNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }
}

public class MutiConditionDemo {
    public static void main(String[] args) {
        Source source = new Source();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {

                source.print(Thread.currentThread().getName());
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {

                source.print(Thread.currentThread().getName());
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {

                source.print(Thread.currentThread().getName());
            }
        }, "C").start();


    }


}
