package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.List;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ac */
/* loaded from: classes3.dex */
public final class C2568ac {

    /* renamed from: a */
    public static int f1054a = 1000;

    /* renamed from: b */
    public static long f1055b = 259200000;

    /* renamed from: d */
    private static C2568ac f1056d;

    /* renamed from: i */
    private static String f1057i;

    /* renamed from: c */
    public final C2576ak f1058c;

    /* renamed from: e */
    private final List<AbstractC2627o> f1059e;

    /* renamed from: f */
    private final StrategyBean f1060f;

    /* renamed from: g */
    private StrategyBean f1061g = null;

    /* renamed from: h */
    private Context f1062h;

    private C2568ac(Context context, List<AbstractC2627o> list) {
        this.f1062h = context;
        if (C2566aa.m646a(context) != null) {
            String str = C2566aa.m646a(context).f983H;
            if ("oversea".equals(str)) {
                StrategyBean.f868a = "https://astat.bugly.qcloud.com/rqd/async";
                StrategyBean.f869b = "https://astat.bugly.qcloud.com/rqd/async";
            } else if ("na_https".equals(str)) {
                StrategyBean.f868a = "https://astat.bugly.cros.wr.pvp.net/:8180/rqd/async";
                StrategyBean.f869b = "https://astat.bugly.cros.wr.pvp.net/:8180/rqd/async";
            }
        }
        this.f1060f = new StrategyBean();
        this.f1059e = list;
        this.f1058c = C2576ak.m772a();
    }

    /* renamed from: a */
    public static synchronized C2568ac m711a(Context context, List<AbstractC2627o> list) {
        C2568ac c2568ac;
        synchronized (C2568ac.class) {
            if (f1056d == null) {
                f1056d = new C2568ac(context, list);
            }
            c2568ac = f1056d;
        }
        return c2568ac;
    }

    /* renamed from: a */
    public static synchronized C2568ac m710a() {
        C2568ac c2568ac;
        synchronized (C2568ac.class) {
            c2568ac = f1056d;
        }
        return c2568ac;
    }

    /* renamed from: b */
    public final synchronized boolean m718b() {
        return this.f1061g != null;
    }

    /* renamed from: c */
    public final StrategyBean m719c() {
        StrategyBean strategyBean = this.f1061g;
        if (strategyBean != null) {
            if (!C2581ap.m852d(strategyBean.f884q)) {
                this.f1061g.f884q = StrategyBean.f868a;
            }
            if (!C2581ap.m852d(this.f1061g.f885r)) {
                this.f1061g.f885r = StrategyBean.f869b;
            }
            return this.f1061g;
        }
        if (!C2581ap.m844b(f1057i) && C2581ap.m852d(f1057i)) {
            this.f1060f.f884q = f1057i;
            this.f1060f.f885r = f1057i;
        }
        return this.f1060f;
    }

    /* renamed from: a */
    protected final void m716a(StrategyBean strategyBean, boolean z) {
        C2577al.m785c("[Strategy] Notify %s", C2631s.class.getName());
        C2631s.m1118a(strategyBean, z);
        for (AbstractC2627o abstractC2627o : this.f1059e) {
            try {
                C2577al.m785c("[Strategy] Notify %s", abstractC2627o.getClass().getName());
                abstractC2627o.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!C2577al.m782a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m712a(String str) {
        if (C2581ap.m844b(str) || !C2581ap.m852d(str)) {
            C2577al.m786d("URL user set is invalid.", new Object[0]);
        } else {
            f1057i = str;
        }
    }

    /* renamed from: a */
    public final void m717a(C2612bt c2612bt) {
        if (c2612bt == null) {
            return;
        }
        if (this.f1061g == null || c2612bt.f1408h != this.f1061g.f882o) {
            StrategyBean strategyBean = new StrategyBean();
            strategyBean.f873f = c2612bt.f1401a;
            strategyBean.f875h = c2612bt.f1403c;
            strategyBean.f874g = c2612bt.f1402b;
            if (C2581ap.m844b(f1057i) || !C2581ap.m852d(f1057i)) {
                if (C2581ap.m852d(c2612bt.f1404d)) {
                    C2577al.m785c("[Strategy] Upload url changes to %s", c2612bt.f1404d);
                    strategyBean.f884q = c2612bt.f1404d;
                }
                if (C2581ap.m852d(c2612bt.f1405e)) {
                    C2577al.m785c("[Strategy] Exception upload url changes to %s", c2612bt.f1405e);
                    strategyBean.f885r = c2612bt.f1405e;
                }
            }
            if (c2612bt.f1406f != null && !C2581ap.m844b(c2612bt.f1406f.f1396a)) {
                strategyBean.f886s = c2612bt.f1406f.f1396a;
            }
            if (c2612bt.f1408h != 0) {
                strategyBean.f882o = c2612bt.f1408h;
            }
            if (c2612bt != null && c2612bt.f1407g != null && c2612bt.f1407g.size() > 0) {
                strategyBean.f887t = c2612bt.f1407g;
                String str = c2612bt.f1407g.get("B11");
                strategyBean.f876i = str != null && str.equals("1");
                String str2 = c2612bt.f1407g.get("B3");
                if (str2 != null) {
                    strategyBean.f890w = Long.parseLong(str2);
                }
                strategyBean.f883p = c2612bt.f1412l;
                strategyBean.f889v = c2612bt.f1412l;
                String str3 = c2612bt.f1407g.get("B27");
                if (str3 != null && str3.length() > 0) {
                    try {
                        int parseInt = Integer.parseInt(str3);
                        if (parseInt > 0) {
                            strategyBean.f888u = parseInt;
                        }
                    } catch (Exception e) {
                        if (!C2577al.m782a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
                String str4 = c2612bt.f1407g.get("B25");
                strategyBean.f878k = str4 != null && str4.equals("1");
            }
            C2577al.m781a("[Strategy] enableCrashReport:%b, enableQuery:%b, enableUserInfo:%b, enableAnr:%b, enableBlock:%b, enableSession:%b, enableSessionTimer:%b, sessionOverTime:%d, enableCocos:%b, strategyLastUpdateTime:%d", Boolean.valueOf(strategyBean.f873f), Boolean.valueOf(strategyBean.f875h), Boolean.valueOf(strategyBean.f874g), Boolean.valueOf(strategyBean.f876i), Boolean.valueOf(strategyBean.f877j), Boolean.valueOf(strategyBean.f880m), Boolean.valueOf(strategyBean.f881n), Long.valueOf(strategyBean.f883p), Boolean.valueOf(strategyBean.f878k), Long.valueOf(strategyBean.f882o));
            this.f1061g = strategyBean;
            if (!C2581ap.m852d(c2612bt.f1404d)) {
                C2577al.m785c("[Strategy] download url is null", new Object[0]);
                this.f1061g.f884q = "";
            }
            if (!C2581ap.m852d(c2612bt.f1405e)) {
                C2577al.m785c("[Strategy] download crashurl is null", new Object[0]);
                this.f1061g.f885r = "";
            }
            C2635w.m1154a().m1176b(2);
            C2637y c2637y = new C2637y();
            c2637y.f1546b = 2;
            c2637y.f1545a = strategyBean.f871d;
            c2637y.f1549e = strategyBean.f872e;
            c2637y.f1551g = C2581ap.m833a(strategyBean);
            C2635w.m1154a().m1175a(c2637y);
            m716a(strategyBean, true);
        }
    }

    /* renamed from: d */
    public static StrategyBean m714d() {
        List<C2637y> m1171a = C2635w.m1154a().m1171a(2);
        if (m1171a == null || m1171a.size() <= 0) {
            return null;
        }
        C2637y c2637y = m1171a.get(0);
        if (c2637y.f1551g != null) {
            return (StrategyBean) C2581ap.m815a(c2637y.f1551g, StrategyBean.CREATOR);
        }
        return null;
    }
}
