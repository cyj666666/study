package gogogo.单例;

import java.util.concurrent.*;

public class Singleton5Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Singleton5 singleton1 = Singleton5.getInstance();
//        Singleton5 singleton2 = Singleton5.getInstance();
//
//        System.out.println(singleton1);
//        System.out.println(singleton2);

//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> {
//                Singleton5 singleton = Singleton5.getInstance();
//                System.out.println(singleton);
//            }).start();
//        }

        Callable<Singleton5> callable = () -> Singleton5.getInstance();

        ExecutorService es = Executors.newFixedThreadPool(2);

//        for (int i = 0; i < 2; i++) {
//            Future<Singleton5> submit = es.submit(callable);
//            System.out.println(submit.get());
//        }

        Future<Singleton5> submit1 = es.submit(callable);
        Future<Singleton5> submit2 = es.submit(callable);
        System.out.println(submit1.get());
        System.out.println(submit2.get());
        es.shutdown();

    }

}
