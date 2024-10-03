package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.Looper;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bg */
/* loaded from: classes3.dex */
public final class C2599bg extends Thread {

    /* renamed from: a */
    public RunnableC2598bf f1308a;

    /* renamed from: g */
    private a f1314g;

    /* renamed from: c */
    private boolean f1310c = false;

    /* renamed from: d */
    private boolean f1311d = true;

    /* renamed from: e */
    private boolean f1312e = false;

    /* renamed from: f */
    private int f1313f = 1;

    /* renamed from: b */
    public boolean f1309b = true;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.bg$a */
    /* loaded from: classes3.dex */
    public interface a {
    }

    /* renamed from: a */
    public final boolean m986a() {
        this.f1310c = true;
        if (!isAlive()) {
            return false;
        }
        try {
            interrupt();
        } catch (Exception e) {
            C2577al.m784b(e);
        }
        C2577al.m786d("MainHandlerChecker is reset to null.", new Object[0]);
        this.f1308a = null;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x006e, code lost:
    
        r2.m984d();
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r8 = this;
            long r0 = java.lang.System.currentTimeMillis()
        L4:
            boolean r2 = r8.f1310c
            if (r2 != 0) goto L99
            com.tencent.bugly.proguard.bf r2 = r8.f1308a     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            r3 = 0
            if (r2 != 0) goto L15
            java.lang.String r2 = "Main handler checker is null. Stop thread monitor."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            com.tencent.bugly.proguard.C2577al.m785c(r2, r3)     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            return
        L15:
            boolean r4 = r2.f1303c     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            if (r4 == 0) goto L26
            r2.f1303c = r3     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            long r4 = android.os.SystemClock.uptimeMillis()     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            r2.f1304d = r4     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            android.os.Handler r4 = r2.f1301a     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            r4.post(r2)     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
        L26:
            r8.m985a(r2)     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            boolean r4 = r8.f1309b     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            if (r4 == 0) goto L6c
            boolean r4 = r8.f1311d     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            if (r4 != 0) goto L32
            goto L6c
        L32:
            long r4 = r2.m982b()     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            r6 = 1510(0x5e6, double:7.46E-321)
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L6c
            r6 = 199990(0x30d36, double:9.8808E-319)
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 < 0) goto L44
            goto L6c
        L44:
            r6 = 5010(0x1392, double:2.4753E-320)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            r5 = 1
            if (r4 > 0) goto L56
            r8.f1313f = r5     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            java.lang.String r4 = "timeSinceMsgSent in [2s, 5s], record stack"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            com.tencent.bugly.proguard.C2577al.m785c(r4, r3)     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
        L54:
            r3 = r5
            goto L6c
        L56:
            int r4 = r8.f1313f     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            int r4 = r4 + r5
            r8.f1313f = r4     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            int r6 = r4 + (-1)
            r4 = r4 & r6
            if (r4 != 0) goto L61
            goto L62
        L61:
            r5 = r3
        L62:
            if (r5 == 0) goto L54
            java.lang.String r4 = "timeSinceMsgSent in (5s, 200s), should record stack:true"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            com.tencent.bugly.proguard.C2577al.m785c(r4, r3)     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            goto L54
        L6c:
            if (r3 == 0) goto L71
            r2.m984d()     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
        L71:
            com.tencent.bugly.proguard.bg$a r3 = r8.f1314g     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            if (r3 == 0) goto L7f
            boolean r3 = r8.f1311d     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            if (r3 == 0) goto L7f
            r2.m981a()     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            r2.m982b()     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
        L7f:
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            long r2 = r2 - r0
            r4 = 500(0x1f4, double:2.47E-321)
            long r2 = r2 % r4
            long r4 = r4 - r2
            com.tencent.bugly.proguard.C2581ap.m840b(r4)     // Catch: java.lang.OutOfMemoryError -> L8d java.lang.Exception -> L93
            goto L4
        L8d:
            r2 = move-exception
            com.tencent.bugly.proguard.C2577al.m784b(r2)
            goto L4
        L93:
            r2 = move-exception
            com.tencent.bugly.proguard.C2577al.m784b(r2)
            goto L4
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2599bg.run():void");
    }

    /* renamed from: a */
    private synchronized void m985a(RunnableC2598bf runnableC2598bf) {
        if (this.f1311d) {
            return;
        }
        if (this.f1312e && !runnableC2598bf.m981a()) {
            C2577al.m785c("Restart getting main stack trace.", new Object[0]);
            this.f1311d = true;
            this.f1312e = false;
        }
    }

    /* renamed from: c */
    public final synchronized void m988c() {
        this.f1311d = false;
        C2577al.m785c("Record stack trace is disabled.", new Object[0]);
    }

    /* renamed from: d */
    public final synchronized void m989d() {
        this.f1312e = true;
    }

    /* renamed from: b */
    public final boolean m987b() {
        Handler handler = new Handler(Looper.getMainLooper());
        RunnableC2598bf runnableC2598bf = this.f1308a;
        if (runnableC2598bf == null) {
            this.f1308a = new RunnableC2598bf(handler, handler.getLooper().getThread().getName());
        } else {
            runnableC2598bf.f1302b = 5000L;
        }
        if (isAlive()) {
            return false;
        }
        try {
            start();
            return true;
        } catch (Exception e) {
            C2577al.m784b(e);
            return false;
        }
    }
}
