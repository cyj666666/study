package gogogo.ForkAndJoin;

import gogogo.util.MyUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: caoyunji
 * @Date: 2020/5/21 17:05
 */
class ForkJoinTaskDemo2 extends RecursiveAction {
    private int start;
    private int end;

    public ForkJoinTaskDemo2(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= 2) {
            if (start == 1) {
                System.out.println("RuntimeException");
                throw new RuntimeException("11111111");
//                while (true) {
//
//                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MyUtils.recordLog("start:" + start + "\tend:" + end);
        } else {
            int mid = (start + end) / 2;
            ForkJoinTaskDemo2 left = new ForkJoinTaskDemo2(start, mid);
            ForkJoinTaskDemo2 right = new ForkJoinTaskDemo2(mid + 1, end);
            left.fork();
            right.fork();
        }
    }
}

public class ForkJoinDemo2 {
    public static void main(String[] args) {
        ForkJoinPool pool1 = (ForkJoinPool) Executors.newWorkStealingPool();
        Instant start = Instant.now();

        ForkJoinTaskDemo2 forkJoinTaskDemo2 = new ForkJoinTaskDemo2(1, 10);
        pool1.submit(forkJoinTaskDemo2);
        System.out.println("FFFFFFFFF");
        System.out.println("FFFFFFFFF");
        try {
            pool1.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("161616161616161616");
        Instant end = Instant.now();

        System.out.println("耗时：" + Duration.between(start, end).toMillis());
    }
}
