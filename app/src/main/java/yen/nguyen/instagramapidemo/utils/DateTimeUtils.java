package yen.nguyen.instagramapidemo.utils;

import android.text.TextUtils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static final String FORMAT_JSON = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_SIMPLE_DISPLAY = "dd MMM HH:mm";
    public static final String FORMAT_TIME_DISPLAY = "HH:mm";
    public static final String FORMAT_TIME_DISPLAY_12H = "HH:mm a";
    public static final String FORMAT_MONTH_YEAR_DISPLAY = "dd MMMM yyyy";
    public static final String FORMAT_WITH_TZ = "yyyy-MM-dd HH:mm:ss:SSS Z";
    public static final String FORMAT_WITH_TZ_SIMPLE = "yyyy-MM-dd HH:mm";


    public static final String DATE_ISO_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_ISO_FORMATTER = DateTimeFormat.forPattern(DATE_ISO_FORMAT);


    private static final SimpleDateFormat FORMATTER_SIMPLE_DATETIME = new SimpleDateFormat(FORMAT_SIMPLE_DISPLAY);
    private static final SimpleDateFormat FORMATTER_SIMPLE_TIME = new SimpleDateFormat(FORMAT_TIME_DISPLAY);
    private static final SimpleDateFormat FORMATTER_SIMPLE_TIME_12HR = new SimpleDateFormat(FORMAT_TIME_DISPLAY_12H);

    private static final DateTimeFormatter FORMATTER_WITH_TZ = DateTimeFormat.forPattern(FORMAT_WITH_TZ);
    private static final DateTimeFormatter FORMAT_SIMPLE_HHMM = DateTimeFormat.forPattern("HH:mm a");
    private static final DateTimeFormatter FORMAT_SIMPLE_HHMM_24 = DateTimeFormat.forPattern("HH:mm");
    private static final DateTimeFormatter FORMATTER_WITH_TZ_SIMPLE = DateTimeFormat.forPattern(FORMAT_WITH_TZ_SIMPLE);

    public static String printWithTz(DateTime dt) {
        if (dt != null) {
            return FORMATTER_WITH_TZ.print(dt);
        }
        return "";
    }

    public static String printWithHumanReadableAndTz(DateTime dt) {
        if (dt != null) {
            DateTime midnightToday = DateTime.now().withTimeAtStartOfDay();
            if (!dt.isBefore(midnightToday)) {
                return "Today " + FORMAT_SIMPLE_HHMM_24.print(dt);
            } else {
                DateTime midnightYesterday = midnightToday.minusDays(1);
                if (!dt.isBefore(midnightYesterday)) {
                    return "Yesterday " + FORMAT_SIMPLE_HHMM_24.print(dt);
                } else {
                    return FORMAT_SIMPLE_HHMM_24.print(dt);
                }
            }
        }

        return "";
    }

    public static DateTime parseWithTz(String dt) {
        if (!TextUtils.isEmpty(dt)) {
            return FORMATTER_WITH_TZ.parseDateTime(dt);
        }
        return null;
    }

    public static DateTime parseWithTz(Long millis) {
        return new DateTime(millis, DateTimeZone.UTC);
    }

    public static String printTimeAsHHmmOfDeviceTz(DateTime dt) {
        if (dt == null) {
            return "";
        }
        return FORMATTER_SIMPLE_TIME_12HR.format(dt.toDate());
    }

    public static String printSimpleDisplayDateTime(DateTime convertedDate) {
        if (convertedDate == null) {
            return "";
        }
        return printSimpleDisplayDateTime(convertedDate.toDate());
    }

    public static String printSimpleDisplayDateTime(Date convertedDate) {
        if (convertedDate == null) {
            return "";
        }
        return FORMATTER_SIMPLE_DATETIME.format(convertedDate);
    }

    public static String printSimpleDisplayTime(DateTime convertedDate) {
        if (convertedDate == null) {
            return "";
        }
        return printSimpleDisplayTime(convertedDate.toDate());
    }

    public static String printSimpleDisplayTime(Date convertedDate) {
        if (convertedDate == null) {
            return "";
        }
        return FORMATTER_SIMPLE_TIME.format(convertedDate);
    }

    public static String parseMonthAndYearDisplayString(Date date) {
        SimpleDateFormat dateOuputFormat = new SimpleDateFormat(FORMAT_MONTH_YEAR_DISPLAY);
        return dateOuputFormat.format(date);
    }

    public static boolean isYesterday(Calendar entityLastModifiedDate) {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_YEAR, -1);
        return yesterday.get(Calendar.YEAR) == entityLastModifiedDate.get(Calendar.YEAR)
                && yesterday.get(Calendar.DAY_OF_YEAR) == entityLastModifiedDate.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isToday(Calendar entityLastModifiedDate) {
        Calendar currentDate = Calendar.getInstance();
        return currentDate.get(Calendar.YEAR) == entityLastModifiedDate.get(Calendar.YEAR)
                && currentDate.get(Calendar.MONTH) == entityLastModifiedDate.get(Calendar.MONTH)
                && currentDate.get(Calendar.DATE) == entityLastModifiedDate.get(Calendar.DATE);
    }

    public static DateTime truncateToSecond(DateTime d) {
        if (d == null) {
            return d;
        }
        return d.secondOfDay().roundFloorCopy();
    }

}
