package gogogo.线程池_手写;

import gogogo.util.MyUtils;

import java.util.concurrent.*;

/**
 * @Author: caoyunji
 * @Date: 2020/5/21 1:22
 */
public class ThreadPoolHander {
    public static void main(String[] args) {


        ExecutorService service = new ThreadPoolExecutor(
                2,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),//千万不能是凶悍版 Integer.MAX
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 0; i < 9; i++) {
                int finalI = i;
                service.execute(() -> {
                    MyUtils.recordLog(String.valueOf(finalI));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }


    }


}
