package practise.syncThreadCode;

import java.util.concurrent.*;

/**
 * @author yjcao
 * @date 2020/6/22 19:59
 */
public class futureAndCallable {
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(20, 50,
                60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));

        Future<String> future = executor.submit(() -> { //Lambda 是一个 callable， 提交后便立即执行，这里返回的是 FutureTask 实例
            System.out.println("running task");
            Thread.sleep(10000);
            return "return task";
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        System.out.println("do something else");  //前面的的 Callable 在其他线程中运行着，可以做一些其他的事情


//        System.out.println(future.get(5, TimeUnit.SECONDS));  //等待 future 的执行结果，执行完毕之后打印出来


        try {
//            while (!future.isDone()) {
//                System.out.println("结果还未获取~~");
//                Thread.sleep(1000);
//            }
            System.out.println(future.get(3, TimeUnit.SECONDS));  //等待 future 的执行结果，执行完毕之后打印出来
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {

        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }


    }


}
