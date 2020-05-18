package gogogo.ForkAndJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkAndJoin {

    //获得执行ForkAndJoin任务的线程池
    private static final ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();
//    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();


    public static void main(String args[]) throws ExecutionException, InterruptedException {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 101000; i++) {
            list.add(i);
        }
        ForkAndJoinRequest request = new ForkAndJoinRequest(0, list.size() - 1, list);
//        ForkJoinTask<Integer> submitFuture = forkJoinPool.submit(request);
        Integer invoke = forkJoinPool.invoke(request);
        System.out.println(request.get());
//        System.out.println(submitFuture.get());
        System.out.println(invoke);
    }


}

//定义request继承RecursiveTask，并实现compute方法
class ForkAndJoinRequest extends RecursiveTask<Integer> {

    private int start;
    private int end;
    private List<Integer> list;

    public ForkAndJoinRequest(int start, int end, List<Integer> list) {
        this.start = start;
        this.end = end;
        this.list = list;
    }

    @Override
    protected Integer compute() {
        int count = end - start;
        if (count <= 25) { //如果需要累加的数量小于等于25，则直接执行
            int result = 0;
            for (int i = start; i <= end; i++) {
                result += i;
            }
            System.out.println(Thread.currentThread() + ":" + result);
            return result;
        } else { //否则fork出其他request
            int mid = (start + end) / 2;
            ForkAndJoinRequest request1 = new ForkAndJoinRequest(start, mid, list);
            request1.fork(); //调用fork方法将自身放入等待队列中等待执行
            ForkAndJoinRequest request2 = new ForkAndJoinRequest(mid + 1, end, list);
            request2.fork();

            //等待执行结果
            return request1.join() + request2.join();

        }
    }
}