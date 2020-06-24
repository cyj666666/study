package practise.syncThreadCode.completableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * anyof:等待任意一个线程完成
 *
 * @author yjcao
 * @date 2020/6/23 14:51
 */
public class AnyofDemo1 {
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


        List<CompletableFuture<?>> completableFutures = new ArrayList<>();
        completableFutures.add(completableFuture1);
        completableFutures.add(completableFuture2);

        CompletableFuture<?>[] completableArray = completableFutures.toArray(new CompletableFuture[completableFutures.size()]);

        //所有的future都完成了才会返回值
        CompletableFuture<Void> completableFutureAllof = CompletableFuture.allOf(completableArray);

        //有任意一个future任务完成了就会返回值
        CompletableFuture<Object> completableFutureAny = CompletableFuture.anyOf(completableArray);

        while (!completableFutureAny.isDone()) {
            System.out.println("阻塞中。。。。");
            Thread.sleep(1000);
        }


        System.out.println(completableFutureAny.get());
        System.out.println("1111111");


    }
}
