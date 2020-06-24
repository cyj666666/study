package practise.syncThreadCode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action。
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
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("run start ...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            int i = 1;
            if (i == 1) {
                throw new RuntimeException("1111");
            }
            System.out.println("run end ...");
        });

//        future.whenComplete((t, action) -> System.out.println("执行完成！"));
        future.exceptionally(t -> {
            System.out.println("执行失败！" + t.getMessage());
            return null;
        });

        TimeUnit.SECONDS.sleep(2);

    }
}
