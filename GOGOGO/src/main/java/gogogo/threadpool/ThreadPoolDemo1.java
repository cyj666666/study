package gogogo.threadpool;

import gogogo.util.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: caoyunji
 * @Date: 2020/5/20 19:22
 */


public class ThreadPoolDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = new ThreadPoolExecutor
//                (8, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();
//        try {
//            for (int i = 0; i < 10; i++) {
//                int finalI = i;
//                executorService.execute(() -> {
//                    MyUtils.recordLog(String.valueOf(finalI));
//
//                });
////                try {
////                    Thread.sleep(30);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            executorService.shutdown();
//        }
//        逐个处理(executorService);
        foreach_execute(executorService);


    }

    private static void fori(ExecutorService executorService) {
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Future<Integer> future = executorService.submit(() -> {
                MyUtils.recordLog(String.valueOf(finalI));
                if (finalI == 2) {
                    throw new RuntimeException("222222Exception");
                }
                return finalI;
            });
            futureList.add(future);
        }

        for (int i = 0; i < futureList.size(); i++) {
            try {
                System.out.println(futureList.get(i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


        System.out.println("over1111111111");
    }

    private static void foreach_execute(ExecutorService executorService) throws ExecutionException, InterruptedException {
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Future<Integer> future = executorService.submit(() -> {
                MyUtils.recordLog(String.valueOf(finalI));
                if (finalI == 2) {
                    throw new RuntimeException("222222Exception");
                }
                MyUtils.recordLog(String.valueOf(finalI)+"结束");
                return finalI;
            });
            futureList.add(future);
        }
        System.out.println("size:" + futureList.size());
        for (Future<Integer> fu : futureList) {
//            try {
                System.out.println(fu.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }
    }

    private static void 逐个处理(ExecutorService executorService) {

        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Future<Integer> future = executorService.submit(() -> {
                MyUtils.recordLog(String.valueOf(finalI));
                if (finalI == 2) {
                    throw new RuntimeException("222222Exception");
                }
                return finalI;
            });
            futureList.add(future);
        }
        try {
            System.out.println(futureList.get(0).get());
            System.out.println(futureList.get(1).get());
            System.out.println(futureList.get(2).get());
            System.out.println(futureList.get(3).get());
            System.out.println(futureList.get(4).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void futureTaskFunction(ExecutorService executorService) throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(() -> {
            return 1024;
        });

        executorService.submit(futureTask1);
        System.out.println(futureTask1.get());
    }
}
