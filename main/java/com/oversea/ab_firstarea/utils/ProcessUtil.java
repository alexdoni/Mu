package com.oversea.ab_firstarea.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes.dex */
public class ProcessUtil {
    private static final String TAG = "ProcessUtil";

    public static boolean isMainProcess(Context context) {
        if (context == null) {
            return true;
        }
        if (context.getPackageName().equals(getCurrentProcessNameByApplication())) {
            Log.i(TAG, "getCurrentProcessNameByApplication");
            return true;
        }
        if (context.getPackageName().equals(getCurrentProcessNameByActivityThread())) {
            Log.i(TAG, "getCurrentProcessNameByActivityThread");
            return true;
        }
        if (!context.getPackageName().equals(getCurrentProcessNameByActivityManager(context))) {
            return false;
        }
        Log.i(TAG, "getCurrentProcessNameByActivityManager");
        return true;
    }

    public static String getCurrentProcessNameByApplication() {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                return Application.getProcessName();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getCurrentProcessNameByActivityThread() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getCurrentProcessNameByActivityManager(Context context) {
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            String packageName = context.getPackageName();
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid && packageName.equals(runningAppProcessInfo.processName)) {
                    return runningAppProcessInfo.processName;
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
