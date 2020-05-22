package gogogo.ForkAndJoin;

import gogogo.util.MyUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: caoyunji
 * @Date: 2020/5/21 17:05
 */
class ForkJoinTaskDemo extends RecursiveTask<List<Integer>> {
    private int start;
    private int end;
    private List<Integer> sourceList;
    private static final int THORD_VALUE = 2;

    public ForkJoinTaskDemo(int start, int end, List<Integer> sourceList) {
        this.start = start;
        this.end = end;
        this.sourceList = sourceList;
    }

    @Override
    protected List<Integer> compute() {
        if (end - start <= THORD_VALUE) {
//            MyUtils.recordLog("start:" + start + "\tend:" + end);
            if (start == 0) {
                throw new RuntimeException("1111111");
            }
            List<Integer> result = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                Integer source = sourceList.get(i);
                source += 1;
                result.add(source);
            }
            MyUtils.recordLog("start:" + start + "\tend:" + end + "\tresult:" + result);
            return result;
        } else {
            int mid = (start + end) / 2;
            ForkJoinTaskDemo left = new ForkJoinTaskDemo(start, mid, sourceList);
            ForkJoinTaskDemo right = new ForkJoinTaskDemo(mid + 1, end, sourceList);
            left.fork();
            right.fork();
            left.join().addAll(right.join());
            return left.join();
        }
    }
}

public class ForkJoinDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool1 = (ForkJoinPool) Executors.newWorkStealingPool();


        List<Integer> sourceList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            sourceList.add(i);
        }

        Instant start = Instant.now();


        ForkJoinTaskDemo forkJoinTaskDemo = new ForkJoinTaskDemo(0, sourceList.size() - 1, sourceList);
        ForkJoinTask<List<Integer>> submit = pool1.submit(forkJoinTaskDemo);

        System.out.println(submit.get());


        Instant end = Instant.now();

        System.out.println("耗时：" + Duration.between(start, end).toMillis());


    }
}
