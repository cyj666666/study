package practise.syncThreadCode.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，
 * 把两个任务的结果一块交给 thenCombine 来处理。
 * @author yjcao
 * @date 2020/6/23 11:08
 */
public class ThenCombineDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });
        //thenCombine的功能更类似thenAcceptBoth，只不过thenAcceptBoth是纯消费，它的函数参数没有返回值，而thenCombine的函数参数fn有返回值。
        CompletableFuture<String> f = future.thenCombine(future2, (x, y) -> y + "-" + x);
        System.out.println(f.get());

    }


}
