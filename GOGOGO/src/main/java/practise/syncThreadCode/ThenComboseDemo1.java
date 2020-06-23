package practise.syncThreadCode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author yjcao
 * @date 2020/6/23 11:14
 */
public class ThenComboseDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "caoyunji");

//        接受一个Function作为参数，这个Function的输入是当前的CompletableFuture的计算值，
//        返回结果将是一个新的CompletableFuture，
//        这个新的CompletableFuture会组合原来的CompletableFuture和函数返回的CompletableFuture。

        CompletableFuture<String> completableFuture2 = completableFuture1.thenCompose(x -> CompletableFuture.supplyAsync(() -> x + "666"));

        CompletableFuture<String> completableFuture3 = completableFuture1.thenCombine(completableFuture2, (x, y) -> {
            return x + "," + y;
        });

        System.out.println(completableFuture3.get());


    }
}
