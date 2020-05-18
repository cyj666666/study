package gogogo.单例;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class Test2 {
    public static void main(String[] args) {
        Instant start = Instant.now();
        long reduce = LongStream.rangeClosed(0, 1100000000)
                //并行流
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(reduce);
        Instant end = Instant.now();
        System.out.println("耗费时间" + Duration.between(start, end).toMillis());

        Instant start2 = Instant.now();


        long reduce1 = LongStream.rangeClosed(0, 1100000000)
                //顺序流
                .sequential()
                .reduce(0, Long::sum);
        System.out.println(reduce1);

        Instant end2 = Instant.now();
        System.out.println("耗费时间" + Duration.between(start2, end2).toMillis());



    }
}
