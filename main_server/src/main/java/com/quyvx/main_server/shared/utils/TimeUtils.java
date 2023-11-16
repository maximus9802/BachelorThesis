package com.quyvx.main_server.shared.utils;

import com.quyvx.main_server.shared.constants.ProjectConstants;
import com.quyvx.main_server.shared.exceptions.BadRequestException;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class TimeUtils {
    private TimeUtils() {

    }

    public static final String HH_MM_SS = "HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_SLASH = "yyyy/MM/dd";

    public static String parseString(LocalTime localTime, String format) {
        return localTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String parseString(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String parseString(LocalDate localDate, String format) {
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    public static LocalDateTime nullOrNow(LocalDateTime time) {
        if (time == null) {
            return TimeUtils.now();
        }
        return time;
    }

    public static Instant getInstant(LocalDateTime time) {
        return time.atZone(ZoneId.of(ProjectConstants.TIMEZONE)).toInstant();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now().atZone(ZoneId.of(ProjectConstants.TIMEZONE)).toLocalDateTime();
    }

    public static LocalDate toLocalDate(String year, String month, String day) {
        if (!isValidDate(year, month, day)) {
            throw new BadRequestException(Map.of("date", "invalid date"));
        }
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    public static boolean isValidDate(String year, String month, String day) {
        try {
            LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static YearMonth toYearMonth(String year, String month) {
        if (!isValidYearMonth(year, month)) {
            throw new BadRequestException((Map.of("month", "invalid month")));
        }
        return YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
    }

    public static boolean isValidYearMonth(String year, String month) {
        try {
            YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String stringifyTime(LocalDate date, int hour, int minute, int second) {
        String template = "%sT%s:%s:%s";
        return String.format(template, date,
                StringUtils.leftPad(String.valueOf(hour), 2, "0"),
                StringUtils.leftPad(String.valueOf(minute), 2, "0"),
                StringUtils.leftPad(String.valueOf(second), 2, "0"));
    }

}
