package practise.syncThreadCode.completableFuture;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * handle 是执行任务完成时对结果的进一步处理。
 * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
 *
 * @author yjcao
 * @date 2020/6/24 11:52
 */
public class HandleDemo1 {
    private static final ExecutorService executorService =
            new ThreadPoolExecutor(20, 30, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("run start ...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            int i = 1;
            if (i == 1) {
                throw new RuntimeException("exception111111111111~~~~~");
            }
            System.out.println("run end ...");
            return "caoyunji";
        }, executorService).handle((s, throwable) -> {
            //从示例中可以看出，在 handle 中可以根据任务是否有异常来进行做相应的后续处理操作。
            //而 thenApply 方法，如果上个任务出现错误，则不会执行 thenApply 方法。
            if (Objects.nonNull(throwable)) {
                return s + ":" + throwable.getMessage();
            }
            return s + ":666";
        });

        System.out.println(future.get());
        TimeUnit.SECONDS.sleep(2);
        executorService.shutdown();

    }


}
