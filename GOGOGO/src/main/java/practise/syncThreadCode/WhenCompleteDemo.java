package practise.syncThreadCode;

import java.util.concurrent.CompletableFuture;

/**
 * @author yjcao
 * @date 2020/6/23 17:22
 */
public class WhenCompleteDemo {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "111");



    }
}
