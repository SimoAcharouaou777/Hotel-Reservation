// src/Utils/DateUtils.java
package Utils;

import java.time.LocalDate;
import java.time.Month;

public class DateUtils {
    public static String getSeason(LocalDate date) {
        Month month = date.getMonth();
        int day = date.getDayOfMonth();

        if ((month == Month.DECEMBER && day >= 21) || month == Month.JANUARY || month == Month.FEBRUARY || (month == Month.MARCH && day < 21)) {
            return "Winter";
        } else if ((month == Month.MARCH && day >= 21) || month == Month.APRIL || month == Month.MAY || (month == Month.JUNE && day < 21)) {
            return "Spring";
        } else if ((month == Month.JUNE && day >= 21) || month == Month.JULY || month == Month.AUGUST || (month == Month.SEPTEMBER && day < 21)) {
            return "Summer";
        } else {
            return "Fall";
        }
    }
}