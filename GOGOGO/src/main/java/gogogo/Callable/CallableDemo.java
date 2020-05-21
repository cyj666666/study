package gogogo.Callable;

import gogogo.util.MyUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: caoyunji
 * @Date: 2020/5/20 16:30
 */
class Thread3 implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        MyUtils.recordLog("call.....");
        Thread.sleep(3000);
        return 1024;
    }
}


public class CallableDemo {
    public static void main(String[] args) throws Exception {
        int resultTemp = 1025;
//        FutureTask<Integer> futureTask = new FutureTask<>(new Thread3());
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            MyUtils.recordLog("call.....");
            Thread.sleep(3000);
            return 1024;
        });

        new Thread(() -> {
            futureGetResult(resultTemp, futureTask);
        }, "BBB").start();

        new Thread(futureTask, "AAA").start();


//        while (!futureTask.isDone()) {
//            System.out.println("waiting......");
//            Thread.sleep(1000);
//        }
    }

    private static void futureGetResult(int resultTemp, FutureTask<Integer> futureTask) {
        try {
            MyUtils.recordLog("开始计算。。。。。");
            MyUtils.recordLog("result：" + (futureTask.get() + resultTemp));
            System.out.println();//尽量放在最后，没毛病
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
