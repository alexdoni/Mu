package com.mu.utility;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.format.Formatter;
import java.io.File;
import java.util.List;

/* loaded from: classes2.dex */
public class MemoryUtility {
    static Handler clearAppMemoryhandler;
    private static ActivityManager mActivityManager;

    /* renamed from: pm */
    private static PackageManager f741pm;
    private Context mContext;
    ActivityManager.MemoryInfo memoryInfo;

    public MemoryUtility(Activity activity) {
        if (activity == null) {
            return;
        }
        this.mContext = activity;
        mActivityManager = (ActivityManager) activity.getSystemService("activity");
        f741pm = activity.getPackageManager();
        this.memoryInfo = new ActivityManager.MemoryInfo();
    }

    public MemoryUtility(Context context) {
        if (context == null) {
            return;
        }
        this.mContext = context;
        mActivityManager = (ActivityManager) context.getSystemService("activity");
        this.memoryInfo = new ActivityManager.MemoryInfo();
    }

    public long getSystemAvaialbeMemorySize() {
        ActivityManager activityManager = mActivityManager;
        if (activityManager == null) {
            return 0L;
        }
        activityManager.getMemoryInfo(this.memoryInfo);
        return this.memoryInfo.availMem / 1024;
    }

    public long getMemoryThreshold() {
        ActivityManager activityManager = mActivityManager;
        if (activityManager == null) {
            return 0L;
        }
        activityManager.getMemoryInfo(this.memoryInfo);
        return this.memoryInfo.threshold / 1024;
    }

    public boolean isLowMemory() {
        ActivityManager activityManager = mActivityManager;
        if (activityManager == null) {
            return false;
        }
        activityManager.getMemoryInfo(this.memoryInfo);
        return this.memoryInfo.lowMemory;
    }

    public long getMemoryLimitResidue() {
        ActivityManager activityManager = mActivityManager;
        if (activityManager == null) {
            return 0L;
        }
        activityManager.getMemoryInfo(this.memoryInfo);
        return (this.memoryInfo.availMem - this.memoryInfo.threshold) / 1024;
    }

    private String formateFileSize(long j) {
        Context context = this.mContext;
        return context == null ? "" : Formatter.formatFileSize(context, j);
    }

    public int GetProcessPid() {
        if (this.mContext == null) {
            return 0;
        }
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : mActivityManager.getRunningAppProcesses()) {
                if (this.mContext.getPackageName().equals(runningAppProcessInfo.processName)) {
                    return runningAppProcessInfo.pid;
                }
            }
        } catch (Exception unused) {
        }
        return 0;
    }

    public int GetProcessPid(String str, List<MyAppInfo> list) {
        if (this.mContext == null) {
            return 0;
        }
        for (int i = 0; i < list.size(); i++) {
            try {
                if (str.equals(list.get(i).getAppName())) {
                    return 1;
                }
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public long GetProcessMemoryInfo(int i) {
        if (this.mContext == null) {
            return 0L;
        }
        try {
            return mActivityManager.getProcessMemoryInfo(new int[]{i})[0].dalvikPrivateDirty;
        } catch (Exception unused) {
            return 0L;
        }
    }

    public void clearAppMemory() {
        new ClearAppMemoryThread().start();
    }

    public void clearAppMemory(Handler handler) {
        clearAppMemoryhandler = handler;
        new ClearAppMemoryThread().start();
    }

    /* loaded from: classes2.dex */
    private class ClearAppMemoryThread extends Thread {
        private ClearAppMemoryThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (MemoryUtility.mActivityManager != null && MemoryUtility.f741pm != null) {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = MemoryUtility.mActivityManager.getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    for (int i = 0; i < runningAppProcesses.size(); i++) {
                        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
                        if (runningAppProcessInfo.importance > 200) {
                            for (String str : runningAppProcessInfo.pkgList) {
                                try {
                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                if (MemoryUtility.clearAppMemoryhandler != null) {
                    MemoryUtility.clearAppMemoryhandler.sendEmptyMessage(0);
                }
            }
        }
    }

    public void getEveryAppMemory() {
        ActivityManager activityManager;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        Context context = this.mContext;
        if (context == null || (runningAppProcesses = (activityManager = (ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) == null) {
            return;
        }
        for (int i = 0; i < runningAppProcesses.size(); i++) {
            int i2 = activityManager.getProcessMemoryInfo(new int[]{runningAppProcesses.get(i).pid})[0].dalvikPrivateDirty / 1024;
        }
    }

    public void clearAppCache(Context context) {
        File[] listFiles;
        if (this.mContext == null || (listFiles = context.getCacheDir().listFiles()) == null) {
            return;
        }
        for (File file : listFiles) {
            file.delete();
        }
    }
}
