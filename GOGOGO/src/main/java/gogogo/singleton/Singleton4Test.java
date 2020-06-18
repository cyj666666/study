package gogogo.singleton;

import java.util.concurrent.*;

public class Singleton4Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Singleton4 singleton1 = Singleton4.getInstance();
//        Singleton4 singleton2 = Singleton4.getInstance();
//
//        System.out.println(singleton1);
//        System.out.println(singleton2);

//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> {
//                Singleton4 singleton = Singleton4.getInstance();
//                System.out.println(singleton);
//            }).start();
//        }

        Callable<Singleton4> callable = () -> Singleton4.getInstance();

        ExecutorService es = Executors.newFixedThreadPool(2);

//        for (int i = 0; i < 2; i++) {
//            Future<Singleton4> submit = es.submit(callable);
//            System.out.println(submit.get());
//        }

        Future<Singleton4> submit1 = es.submit(callable);
        Future<Singleton4> submit2 = es.submit(callable);
        System.out.println(submit1.get());
        System.out.println(submit2.get());
        es.shutdown();

    }

}
