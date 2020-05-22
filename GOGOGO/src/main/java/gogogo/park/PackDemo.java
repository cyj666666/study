package gogogo.park;

import gogogo.util.MyUtils;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: caoyunji
 * @Date: 2020/5/22 20:27
 */
public class PackDemo {


    public static void main(String[] args) {

        ReentrantLock lock=new ReentrantLock(true);

        AB ab=new AB();
        Thread b=new Thread(){
            @Override
            public void run() {
                int i=3;
                while ((i--)>=0){
                    MyUtils.recordLog("1");
                    LockSupport.park(this);
                    MyUtils.recordLog("B");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    LockSupport.unpark(ab.getA());
                }


            }
        };
        Thread a=new Thread(){
            @Override
            public void run() {
                int i=3;
                while ((i--)>=0){
                    MyUtils.recordLog("A");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    LockSupport.unpark(ab.getB());

                    LockSupport.park();
                }




            }
        };
        ab.setA(a);
        ab.setB(b);

        a.start();
        b.start();

    }





}


class AB{

    private Thread a;
    private Thread b;

    public Thread getA() {
        return a;
    }

    public void setA(Thread a) {
        this.a = a;
    }

    public Thread getB() {
        return b;
    }

    public void setB(Thread b) {
        this.b = b;
    }
}