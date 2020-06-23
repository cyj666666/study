package practise.syncThreadCode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.*;

/**
 * @author yjcao
 * @date 2020/6/23 13:37
 */
public class ThenAcceptDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "caoyunji");

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "caoyunji");

        //thenAccept只对结果执行Action，而不返回新的计算值。
        CompletableFuture<Void> completableFuture3 = completableFuture1.thenAccept(System.out::println);


        CompletableFuture<Void> completableFuture4 = completableFuture1.thenAcceptBoth(completableFuture2, (x, y) -> System.out.println(x + "__" + y));
        CompletableFuture<Void> completableFuture5 = completableFuture1.thenAcceptBoth(CompletableFuture.completedFuture(20), (x, y) -> System.out.println(x + "__" + y));


//        CompletableFuture<Integer> completableFuture3 = CompletableFuture.completedFuture(20);
//        System.out.println(completableFuture3.get());

        System.out.println(completableFuture3.get());


        //Java8 四大函数式接口
        Function function = new Function() {
            @Override
            public Object apply(Object o) {
                return null;
            }
        };

        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {

            }
        };

//        BiConsumer biConsumer = new BiConsumer() {
//            @Override
//            public void accept(Object o, Object o2) {
//
//            }
//        };

        Predicate predicate = new Predicate() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        };

        Supplier supplier = new Supplier() {
            @Override
            public Object get() {
                return null;
            }
        };


    }
}
