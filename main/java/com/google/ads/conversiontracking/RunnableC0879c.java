package com.google.ads.conversiontracking;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PowerManager;
import android.os.Process;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.ads.conversiontracking.c */
/* loaded from: classes.dex */
public class RunnableC0879c implements Runnable {

    /* renamed from: a */
    private static final long f406a = TimeUnit.SECONDS.toMillis(3600);

    /* renamed from: b */
    private static final long f407b = TimeUnit.SECONDS.toMillis(30);

    /* renamed from: c */
    private static Object f408c = new Object();

    /* renamed from: d */
    private static RunnableC0879c f409d;

    /* renamed from: e */
    private final long f410e;

    /* renamed from: f */
    private final long f411f;

    /* renamed from: g */
    private final C0878b f412g;

    /* renamed from: h */
    private final Context f413h;

    /* renamed from: i */
    private final HandlerThread f414i;

    /* renamed from: m */
    private final SharedPreferences f418m;

    /* renamed from: n */
    private long f419n;

    /* renamed from: o */
    private Handler f420o;

    /* renamed from: j */
    private final Object f415j = new Object();

    /* renamed from: l */
    private final Map<String, Long> f417l = new HashMap();

    /* renamed from: k */
    private final Set<String> f416k = new HashSet();

    /* renamed from: a */
    public static RunnableC0879c m127a(Context context) {
        synchronized (f408c) {
            if (f409d == null) {
                try {
                    f409d = new RunnableC0879c(context, f406a, f407b, new C0878b(context));
                } catch (Exception e) {
                    Log.e("GoogleConversionReporter", "Error starting automated usage thread", e);
                }
            }
        }
        return f409d;
    }

    RunnableC0879c(Context context, long j, long j2, C0878b c0878b) {
        this.f413h = context;
        this.f411f = j;
        this.f410e = j2;
        this.f412g = c0878b;
        this.f418m = context.getSharedPreferences("google_auto_usage", 0);
        m131d();
        HandlerThread handlerThread = new HandlerThread("Google Conversion SDK", 10);
        this.f414i = handlerThread;
        handlerThread.start();
        this.f420o = new Handler(handlerThread.getLooper());
        m130c();
    }

    /* renamed from: a */
    public void m133a(String str) {
        synchronized (this.f415j) {
            this.f416k.remove(str);
        }
        m136c(str);
    }

    /* renamed from: b */
    public void m135b(String str) {
        synchronized (this.f415j) {
            this.f416k.add(str);
            this.f417l.remove(str);
        }
    }

    /* renamed from: c */
    public void m136c(String str) {
        synchronized (this.f415j) {
            if (!this.f416k.contains(str) && !this.f417l.containsKey(str)) {
                this.f412g.m126a(str, this.f419n);
                this.f417l.put(str, Long.valueOf(this.f419n));
            }
        }
    }

    /* renamed from: d */
    public boolean m137d(String str) {
        return this.f417l.containsKey(str);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!m134a()) {
            m132a(this.f410e);
            return;
        }
        synchronized (this.f415j) {
            for (Map.Entry<String, Long> entry : this.f417l.entrySet()) {
                String key = entry.getKey();
                long longValue = entry.getValue().longValue();
                long j = this.f419n;
                if (longValue < j) {
                    entry.setValue(Long.valueOf(j));
                    this.f412g.m126a(key, this.f419n);
                }
            }
        }
        m130c();
        m129b(m128b());
    }

    /* renamed from: b */
    private long m128b() {
        long m161a = C0883g.m161a();
        long j = this.f419n;
        return j + ((m161a >= j ? ((m161a - j) / this.f411f) + 1 : 0L) * this.f411f);
    }

    /* renamed from: c */
    private void m130c() {
        synchronized (this.f415j) {
            m132a(m128b() - C0883g.m161a());
        }
    }

    /* renamed from: a */
    protected void m132a(long j) {
        synchronized (this.f415j) {
            Handler handler = this.f420o;
            if (handler != null) {
                handler.removeCallbacks(this);
                this.f420o.postDelayed(this, j);
            }
        }
    }

    /* renamed from: d */
    private void m131d() {
        if (this.f419n == 0) {
            this.f419n = this.f418m.getLong("end_of_interval", C0883g.m161a() + this.f411f);
        }
    }

    /* renamed from: b */
    private void m129b(long j) {
        this.f418m.edit().putLong("end_of_interval", j).commit();
        this.f419n = j;
    }

    /* renamed from: a */
    protected boolean m134a() {
        ActivityManager activityManager = (ActivityManager) this.f413h.getSystemService("activity");
        KeyguardManager keyguardManager = (KeyguardManager) this.f413h.getSystemService("keyguard");
        PowerManager powerManager = (PowerManager) this.f413h.getSystemService("power");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (Process.myPid() == runningAppProcessInfo.pid && runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && powerManager.isScreenOn()) {
                return true;
            }
        }
        return false;
    }
}
