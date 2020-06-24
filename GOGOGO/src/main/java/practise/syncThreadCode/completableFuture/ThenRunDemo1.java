package practise.syncThreadCode.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 该方法同 thenAccept 方法类似。
 * 不同的是上个任务处理完成后，并不会把计算的结果传给 thenRun 方法。
 * 只是处理玩任务后，执行 thenRun的后续操作。
 *
 * @author yjcao
 * @date 2020/6/23 13:37
 */
public class ThenRunDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("run start~~");
            int i = 1;
//            if (i == 1) {
//                throw new RuntimeException("RuntimeException");
//            }
            return "caoyunji";
        }).thenRun(() -> {
            System.out.println("继续执行任务~~");
        });
        System.out.println(completableFuture1.get());
    }
}
