package gogogo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: caoyunji
 * @Date: 2020/5/20 18:27
 */
public class MyUtils {
    public static void recordLog(String log) {
        System.out.println(getLocaldateTimeNow() + "\t" + Thread.currentThread().getName() + "\t" + log);
    }

    private static String getLocaldateTimeNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
    }


}
