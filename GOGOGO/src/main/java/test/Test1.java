package test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Test1 {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDate monDay = now.with(DayOfWeek.MONDAY);
        System.out.println(monDay);
    }
}
