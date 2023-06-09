package tn.iit.utils;


import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

public class DateUtils {
    public static int getRemainingWeeksOfYear() {
        LocalDate currentDate = LocalDate.now();
        YearMonth currentYearMonth = YearMonth.from(currentDate);
        int remainingWeeks = currentYearMonth.lengthOfMonth() - currentDate.getDayOfMonth();
        for (Month month = currentYearMonth.getMonth().plus(1); month.getValue() <= 12; month = month.plus(1)) {
            remainingWeeks += YearMonth.of(currentYearMonth.getYear(), month).lengthOfMonth();
        }
        return remainingWeeks / 7;
    }
}
