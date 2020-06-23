package practise.syncThreadCode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author yjcao
 * @date 2020/6/23 10:57
 */
public class ThenApplyDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        //thenApply功能相当于将CompletableFuture<T>转换成CompletableFuture<U>。
        CompletableFuture<String> f = future.thenApplyAsync(i -> i * 10).thenApply(i -> i.toString()+"cccc");
        System.out.println(f.get());

    }
}
