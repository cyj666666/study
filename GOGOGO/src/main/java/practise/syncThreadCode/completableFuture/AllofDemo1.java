package practise.syncThreadCode.completableFuture;

import java.util.concurrent.*;

/**
 * allof：等待所有线程都完成
 *
 * @author yjcao
 * @date 2020/6/23 14:51
 */
public class AllofDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("completable1  start");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "caoyunji";
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("completable2  start");
            return "666";
        });


        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFuture1, completableFuture2);


        while (!completableFuture.isDone()) {
            System.out.println("阻塞中。。。。");
            Thread.sleep(1000);
        }


        System.out.println(completableFuture.get());
        System.out.println("1111111");


    }
}
