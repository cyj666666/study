package practise.syncThreadCode.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author yjcao
 * @date 2020/6/23 17:00
 */
public class GetNow {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish");
            return "caoyunji";
        });

        Thread.sleep(2000);
//        completableFuture.join();
        System.out.println(completableFuture.getNow("caoyunji666"));
        System.out.println("111111111111");
    }
}