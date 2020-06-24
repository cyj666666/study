package practise.syncThreadCode.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
 * 有返回值，
 * 但是发生异常的场景无法处理
 * <p>
 * Function<? super T,? extends U>
 * T：上一个任务返回结果的类型
 * U：当前任务的返回值类型
 *
 * @author yjcao
 * @date 2020/6/23 10:57
 */
public class ThenApplyDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("run start...");
            int i = 1;
            if (i == 1) {
                throw new RuntimeException("11111111");
            }
            return 100;
        });
        //thenApply功能相当于将CompletableFuture<T>转换成CompletableFuture<U>。
        CompletableFuture<String> f = future.thenApplyAsync(i -> {
            System.out.println("run start 2...");
            return i * 10;
        }).thenApply(i -> i.toString() + "cccc");
        System.out.println(f.get());

    }
}
