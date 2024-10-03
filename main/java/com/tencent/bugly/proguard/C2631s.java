package com.tencent.bugly.proguard;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C2630r;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.s */
/* loaded from: classes3.dex */
public class C2631s {

    /* renamed from: a */
    public static boolean f1487a = false;

    /* renamed from: b */
    public static C2630r f1488b = null;

    /* renamed from: c */
    private static int f1489c = 10;

    /* renamed from: d */
    private static long f1490d = 300000;

    /* renamed from: e */
    private static long f1491e = 30000;

    /* renamed from: f */
    private static long f1492f = 0;

    /* renamed from: g */
    private static int f1493g = 0;

    /* renamed from: h */
    private static long f1494h = 0;

    /* renamed from: i */
    private static long f1495i = 0;

    /* renamed from: j */
    private static long f1496j = 0;

    /* renamed from: k */
    private static Application.ActivityLifecycleCallbacks f1497k = null;

    /* renamed from: l */
    private static Class<?> f1498l = null;

    /* renamed from: m */
    private static boolean f1499m = true;

    /* renamed from: g */
    static /* synthetic */ int m1128g() {
        int i = f1493g;
        f1493g = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005c  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m1124c(android.content.Context r11, com.tencent.bugly.BuglyStrategy r12) {
        /*
            r0 = 1
            r1 = 0
            if (r12 == 0) goto Ld
            boolean r2 = r12.recordUserInfoOnceADay()
            boolean r12 = r12.isEnableUserInfo()
            goto Lf
        Ld:
            r12 = r0
            r2 = r1
        Lf:
            if (r2 == 0) goto L5d
            com.tencent.bugly.proguard.aa r12 = com.tencent.bugly.proguard.C2566aa.m646a(r11)
            java.lang.String r2 = r12.f1028d
            java.util.List r2 = com.tencent.bugly.proguard.C2630r.m1095a(r2)
            if (r2 == 0) goto L58
            r3 = r1
        L1e:
            int r4 = r2.size()
            if (r3 >= r4) goto L58
            java.lang.Object r4 = r2.get(r3)
            com.tencent.bugly.crashreport.biz.UserInfoBean r4 = (com.tencent.bugly.crashreport.biz.UserInfoBean) r4
            java.lang.String r5 = r4.f859n
            java.lang.String r6 = r12.f1039o
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L55
            int r5 = r4.f847b
            if (r5 != r0) goto L55
            long r5 = com.tencent.bugly.proguard.C2581ap.m836b()
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L58
            long r9 = r4.f850e
            int r5 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r5 < 0) goto L55
            long r2 = r4.f851f
            int r12 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r12 > 0) goto L53
            com.tencent.bugly.proguard.r r12 = com.tencent.bugly.proguard.C2631s.f1488b
            r12.m1112b()
        L53:
            r12 = r1
            goto L59
        L55:
            int r3 = r3 + 1
            goto L1e
        L58:
            r12 = r0
        L59:
            if (r12 != 0) goto L5c
            return
        L5c:
            r12 = r1
        L5d:
            com.tencent.bugly.proguard.aa r2 = com.tencent.bugly.proguard.C2566aa.m648b()
            if (r2 == 0) goto L6c
            boolean r3 = com.tencent.bugly.proguard.C2638z.m1182a()
            if (r3 == 0) goto L6c
            r2.m651a(r1, r0)
        L6c:
            if (r12 == 0) goto L9b
            android.content.Context r12 = r11.getApplicationContext()
            boolean r12 = r12 instanceof android.app.Application
            if (r12 == 0) goto L7d
            android.content.Context r11 = r11.getApplicationContext()
            android.app.Application r11 = (android.app.Application) r11
            goto L7e
        L7d:
            r11 = 0
        L7e:
            if (r11 == 0) goto L9b
            android.app.Application$ActivityLifecycleCallbacks r12 = com.tencent.bugly.proguard.C2631s.f1497k     // Catch: java.lang.Exception -> L91
            if (r12 != 0) goto L8b
            com.tencent.bugly.proguard.s$a r12 = new com.tencent.bugly.proguard.s$a     // Catch: java.lang.Exception -> L91
            r12.<init>()     // Catch: java.lang.Exception -> L91
            com.tencent.bugly.proguard.C2631s.f1497k = r12     // Catch: java.lang.Exception -> L91
        L8b:
            android.app.Application$ActivityLifecycleCallbacks r12 = com.tencent.bugly.proguard.C2631s.f1497k     // Catch: java.lang.Exception -> L91
            r11.registerActivityLifecycleCallbacks(r12)     // Catch: java.lang.Exception -> L91
            goto L9b
        L91:
            r11 = move-exception
            boolean r12 = com.tencent.bugly.proguard.C2577al.m782a(r11)
            if (r12 != 0) goto L9b
            r11.printStackTrace()
        L9b:
            boolean r11 = com.tencent.bugly.proguard.C2631s.f1499m
            if (r11 == 0) goto Lbe
            long r11 = java.lang.System.currentTimeMillis()
            com.tencent.bugly.proguard.C2631s.f1495i = r11
            com.tencent.bugly.proguard.r r11 = com.tencent.bugly.proguard.C2631s.f1488b
            r11.m1110a(r0, r1)
            java.lang.String r11 = "[session] launch app, new start"
            java.lang.Object[] r12 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.C2577al.m781a(r11, r12)
            com.tencent.bugly.proguard.r r11 = com.tencent.bugly.proguard.C2631s.f1488b
            r11.m1109a()
            com.tencent.bugly.proguard.r r11 = com.tencent.bugly.proguard.C2631s.f1488b
            r0 = 21600000(0x1499700, double:1.0671818E-316)
            r11.m1111a(r0)
        Lbe:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2631s.m1124c(android.content.Context, com.tencent.bugly.BuglyStrategy):void");
    }

    /* renamed from: a */
    public static void m1117a(final Context context, final BuglyStrategy buglyStrategy) {
        long j;
        if (f1487a) {
            return;
        }
        f1499m = C2566aa.m646a(context).f1030f;
        f1488b = new C2630r(context, f1499m);
        f1487a = true;
        if (buglyStrategy != null) {
            f1498l = buglyStrategy.getUserInfoActivity();
            j = buglyStrategy.getAppReportDelay();
        } else {
            j = 0;
        }
        if (j <= 0) {
            m1124c(context, buglyStrategy);
        } else {
            C2576ak.m772a().m775a(new Runnable() { // from class: com.tencent.bugly.proguard.s.1
                @Override // java.lang.Runnable
                public final void run() {
                    C2631s.m1124c(context, buglyStrategy);
                }
            }, j);
        }
    }

    /* renamed from: a */
    public static void m1115a(long j) {
        if (j < 0) {
            j = C2568ac.m710a().m719c().f883p;
        }
        f1492f = j;
    }

    /* renamed from: a */
    public static void m1118a(StrategyBean strategyBean, boolean z) {
        C2630r c2630r = f1488b;
        if (c2630r != null && !z) {
            c2630r.m1112b();
        }
        if (strategyBean == null) {
            return;
        }
        if (strategyBean.f883p > 0) {
            f1491e = strategyBean.f883p;
        }
        if (strategyBean.f888u > 0) {
            f1489c = strategyBean.f888u;
        }
        if (strategyBean.f889v > 0) {
            f1490d = strategyBean.f889v;
        }
    }

    /* renamed from: a */
    public static void m1114a() {
        C2630r c2630r = f1488b;
        if (c2630r != null) {
            c2630r.m1110a(2, false);
        }
    }

    /* renamed from: a */
    public static void m1116a(Context context) {
        if (!f1487a || context == null) {
            return;
        }
        Application application = context.getApplicationContext() instanceof Application ? (Application) context.getApplicationContext() : null;
        if (application != null) {
            try {
                Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = f1497k;
                if (activityLifecycleCallbacks != null) {
                    application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
                }
            } catch (Exception e) {
                if (!C2577al.m782a(e)) {
                    e.printStackTrace();
                }
            }
        }
        f1487a = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.s$a */
    /* loaded from: classes3.dex */
    public static class a implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            C2577al.m785c(">>> %s onStop <<<", activity.getClass().getName());
            C2566aa.m648b().m651a(activity.hashCode(), false);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            C2577al.m785c(">>> %s onStart <<<", activity.getClass().getName());
            C2566aa.m648b().m651a(activity.hashCode(), true);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
            String name = activity.getClass().getName();
            if (C2631s.f1498l == null || C2631s.f1498l.getName().equals(name)) {
                C2577al.m785c(">>> %s onResumed <<<", name);
                C2566aa m648b = C2566aa.m648b();
                if (m648b == null) {
                    return;
                }
                m648b.f987L.add(C2631s.m1113a(name, "onResumed"));
                m648b.f1049y = name;
                m648b.f1050z = System.currentTimeMillis();
                m648b.f978C = m648b.f1050z - C2631s.f1495i;
                long j = m648b.f1050z - C2631s.f1494h;
                if (j > (C2631s.f1492f > 0 ? C2631s.f1492f : C2631s.f1491e)) {
                    m648b.m657c();
                    C2631s.m1128g();
                    C2577al.m781a("[session] launch app one times (app in background %d seconds and over %d seconds)", Long.valueOf(j / 1000), Long.valueOf(C2631s.f1491e / 1000));
                    if (C2631s.f1493g % C2631s.f1489c == 0) {
                        C2631s.f1488b.m1110a(4, C2631s.f1499m);
                        return;
                    }
                    C2631s.f1488b.m1110a(4, false);
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - C2631s.f1496j > C2631s.f1490d) {
                        long unused = C2631s.f1496j = currentTimeMillis;
                        C2577al.m781a("add a timer to upload hot start user info", new Object[0]);
                        if (C2631s.f1499m) {
                            C2576ak.m772a().m775a(new C2630r.a(null, true), C2631s.f1490d);
                        }
                    }
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
            String name = activity.getClass().getName();
            if (C2631s.f1498l == null || C2631s.f1498l.getName().equals(name)) {
                C2577al.m785c(">>> %s onPaused <<<", name);
                C2566aa m648b = C2566aa.m648b();
                if (m648b == null) {
                    return;
                }
                m648b.f987L.add(C2631s.m1113a(name, "onPaused"));
                m648b.f976A = System.currentTimeMillis();
                m648b.f977B = m648b.f976A - m648b.f1050z;
                long unused = C2631s.f1494h = m648b.f976A;
                if (m648b.f977B < 0) {
                    m648b.f977B = 0L;
                }
                m648b.f1049y = "background";
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            String name = activity.getClass().getName();
            if (C2631s.f1498l == null || C2631s.f1498l.getName().equals(name)) {
                C2577al.m785c(">>> %s onDestroyed <<<", name);
                C2566aa m648b = C2566aa.m648b();
                if (m648b != null) {
                    m648b.f987L.add(C2631s.m1113a(name, "onDestroyed"));
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
            String name = activity.getClass().getName();
            if (C2631s.f1498l == null || C2631s.f1498l.getName().equals(name)) {
                C2577al.m785c(">>> %s onCreated <<<", name);
                C2566aa m648b = C2566aa.m648b();
                if (m648b != null) {
                    m648b.f987L.add(C2631s.m1113a(name, "onCreated"));
                }
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ String m1113a(String str, String str2) {
        return C2581ap.m816a() + "  " + str + "  " + str2 + "\n";
    }
}
