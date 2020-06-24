package practise.syncThreadCode.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
 *
 * @author yjcao
 * @date 2020/6/23 13:37
 */
public class ThenAcceptBothDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "caoyunji");

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "caoyunji");

        //thenAccept只对结果执行Action，而不返回新的计算值。
        CompletableFuture<Void> completableFuture3 = completableFuture1.thenAccept(System.out::println);


        CompletableFuture<Void> completableFuture4 = completableFuture1.thenAcceptBoth(completableFuture2, (x, y) -> System.out.println(x + "__" + y));
        CompletableFuture<Void> completableFuture5 = completableFuture1.thenAcceptBoth(CompletableFuture.completedFuture(20), (x, y) -> System.out.println(x + "__" + y));


        System.out.println(completableFuture3.get());
    }
}
