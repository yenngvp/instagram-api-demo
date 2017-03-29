package yen.nguyen.instagramapidemo.utils;

import android.util.Log;


/**
 * Created by yennguyen on 10/25/16.
 */

public final class LogUtil {

    private static final int LOG_LEVEL = Log.DEBUG;

    public static boolean v() {
        return Log.VERBOSE >= LOG_LEVEL;
    }

    public static boolean d() {
        return Log.DEBUG >= LOG_LEVEL;
    }

    public static boolean i() {
        return Log.INFO >= LOG_LEVEL;
    }

    public static boolean w() {
        return Log.WARN >= LOG_LEVEL;
    }

    public static boolean e() {
        return Log.ERROR >= LOG_LEVEL;
    }

    public static void d(String tag, String message) {
        if (d()) {
            Log.d(tag, message);
        }
    }

    public static void d(String tag, String message, Throwable throwable) {
        if (d()) {
            Log.d(tag, message, throwable);
        }
    }

    public static void i(String tag, String message) {
        if (i()) {
            Log.i(tag, message);
        }
    }

    public static void i(String tag, String message, Throwable throwable) {
        if (i()) {
            Log.i(tag, message, throwable);
        }
    }

    public static void v(String tag, String message) {
        if (v()) {
            Log.v(tag, message);
        }
    }

    public static void v(String tag, String message, Throwable throwable) {
        if (v()) {
            Log.v(tag, message, throwable);
        }
    }

    public static void w(String tag, String message) {
        if (w()) {
            Log.w(tag, message);
        }
    }

    public static void w(String tag, String message, Throwable throwable) {
        if (w()) {
            Log.w(tag, message, throwable);
        }
    }

    public static void e(String tag, String message) {
        if (e()) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Throwable throwable) {
        if (e()) {
            Log.e(tag, message, throwable);
        }
    }

}
