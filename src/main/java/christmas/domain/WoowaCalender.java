package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum WoowaCalender {

    WEEKDAY(List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31)),
    WEEKEND(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),

    STAR(List.of(3, 10, 17, 24, 31));

    final List<Integer> date;

    WoowaCalender(List<Integer> date) {
        this.date = date;
    }

    public static boolean isWeekDate(int date) {
        return Arrays.stream(values())
                .filter(calendar -> calendar != STAR)
                .anyMatch(calendar -> Objects.equals(calendar.date, date));
    }


    public static boolean isStarDate(int date) {
        return STAR.date.contains(date);
    }
}
