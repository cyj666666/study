package gogogo.Callable;

/**
 * @Author: caoyunji
 * @Date: 2020/5/20 16:37
 */
class Thread1 extends Thread {
    public void run() {
        System.out.println("111111");
    }

}

class Thread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("222");
        //该异常不会抛出到主线程，即不会影响主线程
        throw new RuntimeException();
    }
}


public class ThreadDemo1 {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();

        Thread thread2 = new Thread(new Thread2());
        thread2.start();

        Thread thread3 = new Thread(() -> {
            System.out.println("3333");
        });
        thread3.start();
        System.out.println("66666666666");
    }

}
