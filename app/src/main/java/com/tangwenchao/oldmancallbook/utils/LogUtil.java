package com.tangwenchao.oldmancallbook.utils;

import android.util.Log;

import com.tangwenchao.oldmancallbook.BuildConfig;

/**
 * Created by tangwenchao
 */
public class LogUtil {

    private static boolean isDebug = BuildConfig.LOG_DEBUG;

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void d(Object obj, String msg) {
        if (isDebug) {
            Log.d(obj.getClass().getSimpleName(), msg);
        }
    }

    public static void e(Object obj, String msg) {
        if (isDebug) {
            Log.e(obj.getClass().getSimpleName(), msg);
        }
    }

    public static void i(Object obj, String msg) {
        if (isDebug) {
            Log.i(obj.getClass().getSimpleName(), msg);
        }
    }
}
