package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.AbstractC2627o;
import com.tencent.bugly.proguard.C2566aa;
import com.tencent.bugly.proguard.C2577al;
import com.tencent.bugly.proguard.C2585at;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class CrashModule extends AbstractC2627o {
    public static final int MODULE_ID = 1004;

    /* renamed from: c */
    private static int f838c;

    /* renamed from: e */
    private static CrashModule f839e = new CrashModule();

    /* renamed from: a */
    private long f840a;

    /* renamed from: b */
    private BuglyStrategy.C2551a f841b;

    /* renamed from: d */
    private boolean f842d = false;

    public static CrashModule getInstance() {
        f839e.f1466id = 1004;
        return f839e;
    }

    public synchronized boolean hasInitialized() {
        return this.f842d;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00ad A[Catch: all -> 0x00e1, TryCatch #0 {, blocks: (B:7:0x0003, B:10:0x0009, B:12:0x0033, B:14:0x0055, B:15:0x0066, B:17:0x0078, B:19:0x007f, B:22:0x0086, B:24:0x0096, B:27:0x009d, B:29:0x00ad, B:31:0x00b5, B:32:0x00bc, B:37:0x00a8, B:38:0x0091, B:39:0x005b, B:40:0x0061), top: B:6:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b5 A[Catch: all -> 0x00e1, TryCatch #0 {, blocks: (B:7:0x0003, B:10:0x0009, B:12:0x0033, B:14:0x0055, B:15:0x0066, B:17:0x0078, B:19:0x007f, B:22:0x0086, B:24:0x0096, B:27:0x009d, B:29:0x00ad, B:31:0x00b5, B:32:0x00bc, B:37:0x00a8, B:38:0x0091, B:39:0x005b, B:40:0x0061), top: B:6:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ba  */
    @Override // com.tencent.bugly.proguard.AbstractC2627o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void init(android.content.Context r5, boolean r6, com.tencent.bugly.BuglyStrategy r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto Le4
            boolean r0 = r4.f842d     // Catch: java.lang.Throwable -> Le1
            if (r0 == 0) goto L9
            goto Le4
        L9:
            java.lang.String r0 = "Initializing crash module."
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.C2577al.m781a(r0, r2)     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.u r0 = com.tencent.bugly.proguard.C2633u.m1134a()     // Catch: java.lang.Throwable -> Le1
            int r2 = com.tencent.bugly.CrashModule.f838c     // Catch: java.lang.Throwable -> Le1
            r3 = 1
            int r2 = r2 + r3
            com.tencent.bugly.CrashModule.f838c = r2     // Catch: java.lang.Throwable -> Le1
            r0.m1147a(r2)     // Catch: java.lang.Throwable -> Le1
            r4.f842d = r3     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.crashreport.CrashReport.setContext(r5)     // Catch: java.lang.Throwable -> Le1
            r4.m608a(r5, r7)     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.BuglyStrategy$a r0 = r4.f841b     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.at r6 = com.tencent.bugly.proguard.C2585at.m905a(r5, r6, r0)     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.av r0 = r6.f1210t     // Catch: java.lang.Throwable -> Le1
            r0.m933a()     // Catch: java.lang.Throwable -> Le1
            if (r7 == 0) goto L61
            int r0 = r7.getCallBackType()     // Catch: java.lang.Throwable -> Le1
            r6.f1206B = r0     // Catch: java.lang.Throwable -> Le1
            boolean r0 = r7.getCloseErrorCallback()     // Catch: java.lang.Throwable -> Le1
            r6.f1207C = r0     // Catch: java.lang.Throwable -> Le1
            boolean r0 = r7.isUploadSpotCrash()     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.C2585at.f1201o = r0     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.aa r0 = com.tencent.bugly.proguard.C2566aa.m646a(r5)     // Catch: java.lang.Throwable -> Le1
            boolean r2 = r7.isEnableRecordAnrMainStack()     // Catch: java.lang.Throwable -> Le1
            r0.f994S = r2     // Catch: java.lang.Throwable -> Le1
            boolean r0 = r7.isEnableCatchAnrTrace()     // Catch: java.lang.Throwable -> Le1
            if (r0 != 0) goto L5b
            com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler r0 = r6.f1211u     // Catch: java.lang.Throwable -> Le1
            r0.disableCatchAnrTrace()     // Catch: java.lang.Throwable -> Le1
            goto L66
        L5b:
            com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler r0 = r6.f1211u     // Catch: java.lang.Throwable -> Le1
            r0.enableCatchAnrTrace()     // Catch: java.lang.Throwable -> Le1
            goto L66
        L61:
            com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler r0 = r6.f1211u     // Catch: java.lang.Throwable -> Le1
            r0.enableCatchAnrTrace()     // Catch: java.lang.Throwable -> Le1
        L66:
            com.tencent.bugly.proguard.aa r0 = com.tencent.bugly.proguard.C2566aa.m648b()     // Catch: java.lang.Throwable -> Le1
            java.lang.String r0 = r0.f1028d     // Catch: java.lang.Throwable -> Le1
            android.content.Context r2 = r6.f1208c     // Catch: java.lang.Throwable -> Le1
            java.lang.String r2 = com.tencent.bugly.proguard.C2638z.m1180a(r2)     // Catch: java.lang.Throwable -> Le1
            boolean r0 = r0.equals(r2)     // Catch: java.lang.Throwable -> Le1
            if (r0 == 0) goto L7d
            com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler r0 = r6.f1211u     // Catch: java.lang.Throwable -> Le1
            r0.removeEmptyNativeRecordFiles()     // Catch: java.lang.Throwable -> Le1
        L7d:
            if (r7 == 0) goto L91
            boolean r0 = r7.isEnableNativeCrashMonitor()     // Catch: java.lang.Throwable -> Le1
            if (r0 == 0) goto L86
            goto L91
        L86:
            java.lang.String r0 = "[crash] Closed native crash monitor!"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.C2577al.m781a(r0, r2)     // Catch: java.lang.Throwable -> Le1
            r6.m913d()     // Catch: java.lang.Throwable -> Le1
            goto L94
        L91:
            r6.m914e()     // Catch: java.lang.Throwable -> Le1
        L94:
            if (r7 == 0) goto La8
            boolean r0 = r7.isEnableANRCrashMonitor()     // Catch: java.lang.Throwable -> Le1
            if (r0 == 0) goto L9d
            goto La8
        L9d:
            java.lang.String r0 = "[crash] Closed ANR monitor!"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.C2577al.m781a(r0, r1)     // Catch: java.lang.Throwable -> Le1
            r6.m916g()     // Catch: java.lang.Throwable -> Le1
            goto Lab
        La8:
            r6.m915f()     // Catch: java.lang.Throwable -> Le1
        Lab:
            if (r7 == 0) goto Lb3
            boolean r0 = r7.isMerged()     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.C2585at.f1191e = r0     // Catch: java.lang.Throwable -> Le1
        Lb3:
            if (r7 == 0) goto Lba
            long r0 = r7.getAppReportDelay()     // Catch: java.lang.Throwable -> Le1
            goto Lbc
        Lba:
            r0 = 0
        Lbc:
            r6.m908a(r0)     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler r6 = r6.f1211u     // Catch: java.lang.Throwable -> Le1
            r6.checkUploadRecordCrash()     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.C2586au.m922a(r5)     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.aq r6 = com.tencent.bugly.proguard.C2582aq.m854a()     // Catch: java.lang.Throwable -> Le1
            java.lang.String r7 = "android.net.conn.CONNECTIVITY_CHANGE"
            r6.m859a(r7)     // Catch: java.lang.Throwable -> Le1
            r6.m858a(r5)     // Catch: java.lang.Throwable -> Le1
            com.tencent.bugly.proguard.u r5 = com.tencent.bugly.proguard.C2633u.m1134a()     // Catch: java.lang.Throwable -> Le1
            int r6 = com.tencent.bugly.CrashModule.f838c     // Catch: java.lang.Throwable -> Le1
            int r6 = r6 - r3
            com.tencent.bugly.CrashModule.f838c = r6     // Catch: java.lang.Throwable -> Le1
            r5.m1147a(r6)     // Catch: java.lang.Throwable -> Le1
            monitor-exit(r4)
            return
        Le1:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        Le4:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.CrashModule.init(android.content.Context, boolean, com.tencent.bugly.BuglyStrategy):void");
    }

    /* renamed from: a */
    private synchronized void m608a(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy == null) {
            return;
        }
        String libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
        if (!TextUtils.isEmpty(libBuglySOFilePath)) {
            C2566aa.m646a(context).f1044t = libBuglySOFilePath;
            C2577al.m781a("setted libBugly.so file path :%s", libBuglySOFilePath);
        }
        if (buglyStrategy.getCrashHandleCallback() != null) {
            this.f841b = buglyStrategy.getCrashHandleCallback();
            C2577al.m781a("setted CrashHanldeCallback", new Object[0]);
        }
        if (buglyStrategy.getAppReportDelay() > 0) {
            long appReportDelay = buglyStrategy.getAppReportDelay();
            this.f840a = appReportDelay;
            C2577al.m781a("setted delay: %d", Long.valueOf(appReportDelay));
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC2627o
    public void onServerStrategyChanged(StrategyBean strategyBean) {
        C2585at m904a;
        if (strategyBean == null || (m904a = C2585at.m904a()) == null) {
            return;
        }
        m904a.f1210t.m934a(strategyBean);
        m904a.f1211u.onStrategyChanged(strategyBean);
        m904a.f1214x.m957b();
    }

    @Override // com.tencent.bugly.proguard.AbstractC2627o
    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}
