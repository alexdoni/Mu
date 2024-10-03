package com.tencent.bugly.proguard;

import android.app.ActivityManager;
import android.os.Process;
import android.text.TextUtils;
import java.util.List;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.az */
/* loaded from: classes3.dex */
public final class C2591az {
    /* renamed from: a */
    public static ActivityManager.ProcessErrorStateInfo m959a(ActivityManager activityManager, long j) {
        if (activityManager == null) {
            C2577al.m785c("get anr state, ActivityManager is null", new Object[0]);
            return null;
        }
        C2577al.m785c("get anr state, timeout:%d", Long.valueOf(j));
        long j2 = j / 500;
        int i = 0;
        while (true) {
            ActivityManager.ProcessErrorStateInfo m961a = m961a(activityManager.getProcessesInErrorState());
            if (m961a == null) {
                C2577al.m785c("found proc state is null", new Object[0]);
            } else {
                if (m961a.condition == 2) {
                    C2577al.m785c("found proc state is anr! proc:%s", m961a.processName);
                    return m961a;
                }
                if (m961a.condition == 1) {
                    C2577al.m785c("found proc state is crashed!", new Object[0]);
                    return null;
                }
            }
            int i2 = i + 1;
            if (i < j2) {
                C2577al.m785c("try the %s times:", Integer.valueOf(i2));
                C2581ap.m840b(500L);
                i = i2;
            } else {
                return m960a("Find process anr, but unable to get anr message.");
            }
        }
    }

    /* renamed from: a */
    private static ActivityManager.ProcessErrorStateInfo m961a(List<ActivityManager.ProcessErrorStateInfo> list) {
        if (list == null || list.isEmpty()) {
            C2577al.m785c("error state info list is null", new Object[0]);
            return null;
        }
        int myPid = Process.myPid();
        for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : list) {
            if (processErrorStateInfo.pid == myPid) {
                if (TextUtils.isEmpty(processErrorStateInfo.longMsg)) {
                    return null;
                }
                C2577al.m785c("found current proc in the error state", new Object[0]);
                return processErrorStateInfo;
            }
        }
        C2577al.m785c("current proc not in the error state", new Object[0]);
        return null;
    }

    /* renamed from: a */
    private static ActivityManager.ProcessErrorStateInfo m960a(String str) {
        ActivityManager.ProcessErrorStateInfo processErrorStateInfo = new ActivityManager.ProcessErrorStateInfo();
        processErrorStateInfo.pid = Process.myPid();
        processErrorStateInfo.processName = C2638z.m1179a(Process.myPid());
        processErrorStateInfo.shortMsg = str;
        return processErrorStateInfo;
    }
}
