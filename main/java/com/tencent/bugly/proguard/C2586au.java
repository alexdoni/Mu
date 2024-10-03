package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.au */
/* loaded from: classes3.dex */
public final class C2586au {

    /* renamed from: a */
    private static C2586au f1228a;

    /* renamed from: b */
    private C2568ac f1229b;

    /* renamed from: c */
    private C2566aa f1230c;

    /* renamed from: d */
    private C2584as f1231d;

    /* renamed from: e */
    private Context f1232e;

    private C2586au(Context context) {
        C2585at m904a = C2585at.m904a();
        if (m904a == null) {
            return;
        }
        this.f1229b = C2568ac.m710a();
        this.f1230c = C2566aa.m646a(context);
        this.f1231d = m904a.f1209s;
        this.f1232e = context;
        C2576ak.m772a().m774a(new Runnable() { // from class: com.tencent.bugly.proguard.au.1
            @Override // java.lang.Runnable
            public final void run() {
                C2586au.m923a(C2586au.this);
            }
        });
    }

    /* renamed from: a */
    public static C2586au m922a(Context context) {
        if (f1228a == null) {
            f1228a = new C2586au(context);
        }
        return f1228a;
    }

    /* renamed from: a */
    public static void m925a(final Thread thread, final int i, final String str, final String str2, final String str3, final Map<String, String> map) {
        C2576ak.m772a().m774a(new Runnable() { // from class: com.tencent.bugly.proguard.au.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (C2586au.f1228a != null) {
                        C2586au.m924a(C2586au.f1228a, thread, i, str, str2, str3, map);
                    } else {
                        C2577al.m787e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                    }
                } catch (Throwable th) {
                    if (!C2577al.m784b(th)) {
                        th.printStackTrace();
                    }
                    C2577al.m787e("[ExtraCrashManager] Crash error %s %s %s", str, str2, str3);
                }
            }
        });
    }

    /* renamed from: a */
    static /* synthetic */ void m923a(C2586au c2586au) {
        C2577al.m785c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            Class<?> cls = Class.forName("com.tencent.bugly.agent.GameAgent");
            c2586au.f1230c.getClass();
            C2581ap.m829a(cls, "sdkPackageName", "com.tencent.bugly");
            C2577al.m785c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        } catch (Throwable unused) {
            C2577al.m781a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m924a(C2586au c2586au, Thread thread, int i, String str, String str2, String str3, Map map) {
        String str4;
        String str5;
        String str6;
        Thread currentThread = thread == null ? Thread.currentThread() : thread;
        if (i == 4) {
            str4 = "Unity";
        } else if (i == 5 || i == 6) {
            str4 = "Cocos";
        } else {
            if (i != 8) {
                C2577al.m786d("[ExtraCrashManager] Unknown extra crash type: %d", Integer.valueOf(i));
                return;
            }
            str4 = "H5";
        }
        C2577al.m787e("[ExtraCrashManager] %s Crash Happen", str4);
        try {
            if (!c2586au.f1229b.m718b()) {
                C2577al.m786d("[ExtraCrashManager] There is no remote strategy, but still store it.", new Object[0]);
            }
            StrategyBean m719c = c2586au.f1229b.m719c();
            if (!m719c.f873f && c2586au.f1229b.m718b()) {
                C2577al.m787e("[ExtraCrashManager] Crash report was closed by remote. Will not upload to Bugly , print local for helpful!", new Object[0]);
                C2584as.m871a(str4, C2581ap.m816a(), c2586au.f1230c.f1028d, currentThread.getName(), str + "\n" + str2 + "\n" + str3, null);
                C2577al.m787e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
            if (i == 5 || i == 6) {
                if (!m719c.f878k) {
                    C2577al.m787e("[ExtraCrashManager] %s report is disabled.", str4);
                    C2577al.m787e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                    return;
                }
            } else if (i == 8 && !m719c.f879l) {
                C2577al.m787e("[ExtraCrashManager] %s report is disabled.", str4);
                C2577al.m787e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
            int i2 = i != 8 ? i : 5;
            CrashDetailBean crashDetailBean = new CrashDetailBean();
            crashDetailBean.f893C = C2567ab.m697j();
            crashDetailBean.f894D = C2567ab.m693f();
            crashDetailBean.f895E = C2567ab.m699l();
            crashDetailBean.f896F = c2586au.f1230c.m670k();
            crashDetailBean.f897G = c2586au.f1230c.m669j();
            crashDetailBean.f898H = c2586au.f1230c.m671l();
            crashDetailBean.f899I = C2567ab.m687b(c2586au.f1232e);
            crashDetailBean.f900J = C2567ab.m694g();
            crashDetailBean.f901K = C2567ab.m695h();
            crashDetailBean.f919b = i2;
            crashDetailBean.f922e = c2586au.f1230c.m665g();
            crashDetailBean.f923f = c2586au.f1230c.f1039o;
            crashDetailBean.f924g = c2586au.f1230c.m675q();
            crashDetailBean.f930m = c2586au.f1230c.m663f();
            crashDetailBean.f931n = String.valueOf(str);
            crashDetailBean.f932o = String.valueOf(str2);
            str5 = "";
            if (str3 != null) {
                String[] split = str3.split("\n");
                str5 = split.length > 0 ? split[0] : "";
                str6 = str3;
            } else {
                str6 = "";
            }
            crashDetailBean.f933p = str5;
            crashDetailBean.f934q = str6;
            crashDetailBean.f935r = System.currentTimeMillis();
            crashDetailBean.f938u = C2581ap.m846c(crashDetailBean.f934q.getBytes());
            crashDetailBean.f943z = C2581ap.m827a(c2586au.f1230c.f992Q, C2585at.f1194h);
            crashDetailBean.f891A = c2586au.f1230c.f1028d;
            crashDetailBean.f892B = currentThread.getName() + "(" + currentThread.getId() + ")";
            crashDetailBean.f902L = c2586au.f1230c.m677s();
            crashDetailBean.f925h = c2586au.f1230c.m674p();
            crashDetailBean.f907Q = c2586au.f1230c.f1001a;
            crashDetailBean.f908R = c2586au.f1230c.m654a();
            crashDetailBean.f911U = c2586au.f1230c.m684z();
            crashDetailBean.f912V = c2586au.f1230c.f1048x;
            crashDetailBean.f913W = c2586au.f1230c.m678t();
            crashDetailBean.f914X = c2586au.f1230c.m683y();
            crashDetailBean.f942y = C2580ao.m800a();
            if (crashDetailBean.f909S == null) {
                crashDetailBean.f909S = new LinkedHashMap();
            }
            if (map != null) {
                crashDetailBean.f909S.putAll(map);
            }
            C2584as.m871a(str4, C2581ap.m816a(), c2586au.f1230c.f1028d, currentThread.getName(), str + "\n" + str2 + "\n" + str3, crashDetailBean);
            if (!c2586au.f1231d.m900a(crashDetailBean, !C2585at.m904a().f1207C)) {
                c2586au.f1231d.m902b(crashDetailBean, false);
            }
            C2577al.m787e("[ExtraCrashManager] Successfully handled.", new Object[0]);
        } catch (Throwable th) {
            try {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
                C2577al.m787e("[ExtraCrashManager] Successfully handled.", new Object[0]);
            } catch (Throwable th2) {
                C2577al.m787e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                throw th2;
            }
        }
    }
}
