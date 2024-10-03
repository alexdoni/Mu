package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.C2572ag;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.at */
/* loaded from: classes3.dex */
public final class C2585at {

    /* renamed from: D */
    private static C2585at f1187D = null;

    /* renamed from: a */
    public static int f1188a = 0;

    /* renamed from: b */
    public static boolean f1189b = false;

    /* renamed from: d */
    public static int f1190d = 2;

    /* renamed from: e */
    public static boolean f1191e = false;

    /* renamed from: f */
    public static int f1192f = 20480;

    /* renamed from: g */
    public static int f1193g = 3000;

    /* renamed from: h */
    public static int f1194h = 20480;

    /* renamed from: i */
    public static long f1195i = 209715200;

    /* renamed from: j */
    public static long f1196j = 604800000;

    /* renamed from: k */
    public static String f1197k = null;

    /* renamed from: l */
    public static boolean f1198l = false;

    /* renamed from: m */
    public static String f1199m = null;

    /* renamed from: n */
    public static int f1200n = 5000;

    /* renamed from: o */
    public static boolean f1201o = true;

    /* renamed from: p */
    public static boolean f1202p = false;

    /* renamed from: q */
    public static String f1203q;

    /* renamed from: r */
    public static String f1204r;

    /* renamed from: A */
    public Boolean f1205A;

    /* renamed from: B */
    public int f1206B = 31;

    /* renamed from: C */
    public boolean f1207C = false;

    /* renamed from: c */
    public final Context f1208c;

    /* renamed from: s */
    public final C2584as f1209s;

    /* renamed from: t */
    public final C2587av f1210t;

    /* renamed from: u */
    public final NativeCrashHandler f1211u;

    /* renamed from: v */
    public final C2568ac f1212v;

    /* renamed from: w */
    public final C2576ak f1213w;

    /* renamed from: x */
    public final C2590ay f1214x;

    /* renamed from: y */
    public BuglyStrategy.C2551a f1215y;

    /* renamed from: z */
    public InterfaceC2588aw f1216z;

    private C2585at(Context context, C2576ak c2576ak, boolean z, BuglyStrategy.C2551a c2551a) {
        f1188a = 1004;
        Context m811a = C2581ap.m811a(context);
        this.f1208c = m811a;
        C2568ac m710a = C2568ac.m710a();
        this.f1212v = m710a;
        this.f1213w = c2576ak;
        this.f1215y = c2551a;
        this.f1216z = null;
        C2584as c2584as = new C2584as(m811a, C2574ai.m746a(), C2635w.m1154a(), m710a, c2551a);
        this.f1209s = c2584as;
        C2566aa m646a = C2566aa.m646a(m811a);
        this.f1210t = new C2587av(m811a, c2584as, m710a, m646a);
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance(m811a, m646a, c2584as, m710a, c2576ak, z, null);
        this.f1211u = nativeCrashHandler;
        m646a.f989N = nativeCrashHandler;
        if (C2590ay.f1257f == null) {
            C2590ay.f1257f = new C2590ay(m811a, m710a, m646a, c2576ak, c2584as);
        }
        this.f1214x = C2590ay.f1257f;
    }

    /* renamed from: a */
    public static synchronized C2585at m905a(Context context, boolean z, BuglyStrategy.C2551a c2551a) {
        C2585at c2585at;
        synchronized (C2585at.class) {
            if (f1187D == null) {
                f1187D = new C2585at(context, C2576ak.m772a(), z, c2551a);
            }
            c2585at = f1187D;
        }
        return c2585at;
    }

    /* renamed from: a */
    public static synchronized C2585at m904a() {
        C2585at c2585at;
        synchronized (C2585at.class) {
            c2585at = f1187D;
        }
        return c2585at;
    }

    /* renamed from: d */
    public final void m913d() {
        this.f1211u.setUserOpened(false);
    }

    /* renamed from: e */
    public final void m914e() {
        this.f1211u.setUserOpened(true);
    }

    /* renamed from: f */
    public final void m915f() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.bugly.proguard.at.1
            @Override // java.lang.Runnable
            public final void run() {
                NativeCrashHandler.getInstance().unBlockSigquit(true);
            }
        });
        this.f1214x.m958b(true);
    }

    /* renamed from: g */
    public final void m916g() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.bugly.proguard.at.2
            @Override // java.lang.Runnable
            public final void run() {
                NativeCrashHandler.getInstance().unBlockSigquit(false);
            }
        });
        this.f1214x.m958b(false);
    }

    /* renamed from: a */
    public final synchronized void m910a(boolean z, boolean z2, boolean z3) {
        this.f1211u.testNativeCrash(z, z2, z3);
    }

    /* renamed from: i */
    public final boolean m918i() {
        return this.f1214x.f1258a.get();
    }

    /* renamed from: a */
    public final void m909a(CrashDetailBean crashDetailBean) {
        this.f1209s.m901b(crashDetailBean);
    }

    /* renamed from: a */
    public final void m908a(long j) {
        C2576ak.m772a().m775a(new Thread() { // from class: com.tencent.bugly.proguard.at.4
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                List<CrashDetailBean> list;
                if (!C2581ap.m830a(C2585at.this.f1208c, "local_crash_lock")) {
                    C2577al.m785c("Failed to lock file for uploading local crash.", new Object[0]);
                    return;
                }
                C2572ag m744a = C2572ag.a.m744a();
                List<C2572ag.b> m736a = C2572ag.m736a();
                if (m736a == null || m736a.isEmpty()) {
                    C2577al.m785c("sla local data is null", new Object[0]);
                } else {
                    C2577al.m785c("sla load local data list size:%s", Integer.valueOf(m736a.size()));
                    Iterator<C2572ag.b> it = m736a.iterator();
                    ArrayList arrayList = new ArrayList();
                    while (it.hasNext()) {
                        C2572ag.b next = it.next();
                        if (next.f1073b < C2581ap.m836b() - 604800000) {
                            C2577al.m785c("sla local data is expired:%s", next.f1074c);
                            arrayList.add(next);
                            it.remove();
                        }
                    }
                    C2572ag.m739d(arrayList);
                    m744a.m743b(m736a);
                }
                List<CrashDetailBean> m866a = C2584as.m866a();
                if (m866a != null && m866a.size() > 0) {
                    C2577al.m785c("Size of crash list: %s", Integer.valueOf(m866a.size()));
                    int size = m866a.size();
                    if (size > 20) {
                        ArrayList arrayList2 = new ArrayList();
                        Collections.sort(m866a);
                        for (int i = 0; i < 20; i++) {
                            arrayList2.add(m866a.get((size - 1) - i));
                        }
                        list = arrayList2;
                    } else {
                        list = m866a;
                    }
                    C2585at.this.f1209s.m899a(list, 0L, false, false, false);
                } else {
                    C2577al.m785c("no crash need to be uploaded at this start", new Object[0]);
                }
                C2581ap.m842b(C2585at.this.f1208c, "local_crash_lock");
            }
        }, j);
    }

    /* renamed from: j */
    public final boolean m919j() {
        return (this.f1206B & 16) > 0;
    }

    /* renamed from: k */
    public final boolean m920k() {
        return (this.f1206B & 8) > 0;
    }

    /* renamed from: b */
    public final synchronized void m911b() {
        this.f1210t.m933a();
        m914e();
        m915f();
    }

    /* renamed from: c */
    public final synchronized void m912c() {
        this.f1210t.m936b();
        m913d();
        m916g();
    }

    /* renamed from: h */
    public final synchronized void m917h() {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i < 30) {
                try {
                    C2577al.m781a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i2));
                    C2581ap.m840b(5000L);
                    i = i2;
                } catch (Throwable th) {
                    if (C2577al.m782a(th)) {
                        return;
                    }
                    th.printStackTrace();
                    return;
                }
            }
        }
    }
}
