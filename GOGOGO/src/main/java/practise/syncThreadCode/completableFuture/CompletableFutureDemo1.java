package practise.syncThreadCode.completableFuture;

import java.util.concurrent.*;

/**
 * @author yjcao
 * @date 2020/6/23 9:24
 * Future模式的缺点
 * Future虽然可以实现获取异步执行结果的需求，但是它没有提供通知的机制，我们无法得知Future什么时候完成。
 * <p>
 * 要么使用阻塞，在future.get()的地方等待future返回的结果，这时又变成同步操作。要么使用isDone()轮询地判断Future是否完成，这样会耗费CPU的资源。
 */
public class CompletableFutureDemo1 {

    private static final ExecutorService executorService = new ThreadPoolExecutor(20, 30, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        runAsync();
//        supplyAsync();

//        Complete();


    }

    private static void Complete() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("1111");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 111;
        }, executorService);

        //会立刻异步执行并且拿到结果，但是如果get()已经拿到结果，则不生效
//        completableFuture.completeExceptionally(new RuntimeException("11111"));
        completableFuture.complete(20);
        System.out.println(completableFuture.get());

        executorService.shutdown();
    }

    private static void supplyAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("1111");
            return "111";
        }, executorService);

//        System.out.println(stringCompletableFuture.get());
        executorService.shutdown();
    }

    private static void runAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("OK");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        System.out.println(voidCompletableFuture.get());

        System.out.println("1111111111");
    }


}
