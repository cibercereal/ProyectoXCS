package es.uvigo.esei.dgss.teama.microstories.domain.entities;

import java.time.*;
import java.util.Date;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.*;

public enum Publication {
    TODAY, THIS_WEEK, THIS_MONTH, THIS_YEAR, ANY_MOMENT;


    public Date[] getFrom() {
        Date[] dates = new Date[2];
        LocalDate today = LocalDate.now();
        switch(this) {
            case TODAY:
                LocalDateTime startOfDay = today.atStartOfDay();
                LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
                Instant instantStart = startOfDay.toInstant(ZoneOffset.UTC);
                Instant instantEnd = endOfDay.toInstant(ZoneOffset.UTC);
                dates[0] = Date.from(instantStart);
                dates[1] = Date.from(instantEnd);
                break;
            case THIS_WEEK:
                LocalDate monday = today.with(previousOrSame(MONDAY));
                LocalDate sunday = today.with(nextOrSame(SUNDAY));
                dates[0] = Date.from(monday.atStartOfDay(ZoneOffset.UTC).toInstant());
                dates[1] = Date.from(sunday.atStartOfDay(ZoneOffset.UTC).toInstant());
                break;
            case THIS_MONTH:
                LocalDate startDate = today.withDayOfMonth(1);
                LocalDate endDate = today.withDayOfMonth(today.lengthOfMonth());
                dates[0] = Date.from(startDate.atStartOfDay(ZoneOffset.UTC).toInstant());
                dates[1] = Date.from(endDate.atStartOfDay(ZoneOffset.UTC).toInstant());
                break;

            case THIS_YEAR:
                LocalDate startDateYear = today.with(firstDayOfYear());
                LocalDate endDateYear = today.with(lastDayOfYear());
                dates[0] = Date.from(startDateYear.atStartOfDay(ZoneOffset.UTC).toInstant());
                dates[1] = Date.from(endDateYear.atStartOfDay(ZoneOffset.UTC).toInstant());
                break;

            case ANY_MOMENT:
                dates[0] = null;
                dates[1] = null;
                break;
        }
        return dates;
    }
}
