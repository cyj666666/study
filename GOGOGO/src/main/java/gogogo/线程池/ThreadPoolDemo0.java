package gogogo.线程池;

import gogogo.util.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: caoyunji
 * @Date: 2020/5/20 19:22
 */


public class ThreadPoolDemo0 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        futureTaskFunction(executorService);

    }

    private static void futureTaskFunction(ExecutorService executorService) throws ExecutionException, InterruptedException {
//        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(() -> {
//            System.out.println("futureTask1");
//            throw new RuntimeException("expection");
////            return 1024;
//        });

        Future<Integer> future1 = executorService.submit(() -> {
            System.out.println("futureTask1");
            throw new RuntimeException("expection");
//            return 1024;
        });
        Future<Integer> future2 = executorService.submit(() -> {
            System.out.println("futureTask2");
            return 1024;
        });

        System.out.println(future1.get());
        System.out.println(future2.get());

        System.out.println("11111");
        executorService.shutdown();
    }
}
