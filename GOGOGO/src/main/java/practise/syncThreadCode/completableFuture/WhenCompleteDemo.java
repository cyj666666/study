package practise.syncThreadCode.completableFuture;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * 当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action(进一步的动作)。
 * <p>
 * 可以看到Action的类型是BiConsumer<? super T,? super Throwable>它可以处理正常的计算结果，或者异常情况。
 * <p>
 * whenComplete 和 whenCompleteAsync 的区别：
 * whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
 * whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
 *
 * @author yjcao
 * @date 2020/6/23 17:22
 */
public class WhenCompleteDemo {
    private static final ExecutorService executorService =
            new ThreadPoolExecutor(20, 30, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("run start ...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            int i = 1;
            if (i != 1) {
                throw new RuntimeException("exception111111111111~~~~~");
            }
            System.out.println("run end ...");
//            return "caoyunji";
        }, executorService).whenComplete((result, exception) -> {
            System.out.println("whenCompleteThread:" + Thread.currentThread().getName());
            System.out.println("t:" + result);
            if (Objects.nonNull(exception)) {
                System.out.println("发生异常：" + exception.getMessage());
            }
            System.out.println("执行完成");
        }).exceptionally(t -> {
            System.out.println("exceptionally:" + t.getMessage());
            return null;
        });
        System.out.println("main:" + Thread.currentThread().getName());

        System.out.println("future:" + future.get());

//        future.whenComplete((t, action) -> System.out.println("执行完成！"));
//        future.exceptionally(t -> {
//            System.out.println("执行失败！" + t.getMessage());
//            return null;
//        });

        TimeUnit.SECONDS.sleep(2);
        executorService.shutdown();

    }
}
