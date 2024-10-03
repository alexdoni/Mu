package com.tencent.bugly.proguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.aq */
/* loaded from: classes3.dex */
public final class C2582aq extends BroadcastReceiver {

    /* renamed from: d */
    private static C2582aq f1157d;

    /* renamed from: b */
    private Context f1159b;

    /* renamed from: c */
    private String f1160c;

    /* renamed from: e */
    private boolean f1161e = true;

    /* renamed from: a */
    private IntentFilter f1158a = new IntentFilter();

    /* renamed from: a */
    public static synchronized C2582aq m854a() {
        C2582aq c2582aq;
        synchronized (C2582aq.class) {
            if (f1157d == null) {
                f1157d = new C2582aq();
            }
            c2582aq = f1157d;
        }
        return c2582aq;
    }

    /* renamed from: a */
    public final synchronized void m859a(String str) {
        if (!this.f1158a.hasAction(str)) {
            this.f1158a.addAction(str);
        }
        C2577al.m785c("add action %s", str);
    }

    /* renamed from: a */
    public final synchronized void m858a(Context context) {
        this.f1159b = context;
        C2581ap.m832a(new Runnable() { // from class: com.tencent.bugly.proguard.aq.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    C2577al.m780a(C2582aq.f1157d.getClass(), "Register broadcast receiver of Bugly.", new Object[0]);
                    synchronized (this) {
                        C2582aq.this.f1159b.registerReceiver(C2582aq.f1157d, C2582aq.this.f1158a, "com.tencent.bugly.BuglyBroadcastReceiver.permission", null);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    /* renamed from: b */
    public final synchronized void m860b(Context context) {
        try {
            C2577al.m780a(getClass(), "Unregister broadcast receiver of Bugly.", new Object[0]);
            context.unregisterReceiver(this);
            this.f1159b = context;
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            m855a(context, intent);
        } catch (Throwable th) {
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private synchronized boolean m855a(Context context, Intent intent) {
        if (context != null && intent != null) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                if (this.f1161e) {
                    this.f1161e = false;
                    return true;
                }
                String m690c = C2567ab.m690c(this.f1159b);
                C2577al.m785c("is Connect BC ".concat(String.valueOf(m690c)), new Object[0]);
                C2577al.m781a("network %s changed to %s", this.f1160c, String.valueOf(m690c));
                if (m690c == null) {
                    this.f1160c = null;
                    return true;
                }
                String str = this.f1160c;
                this.f1160c = m690c;
                long currentTimeMillis = System.currentTimeMillis();
                C2568ac m710a = C2568ac.m710a();
                C2574ai m746a = C2574ai.m746a();
                C2566aa m646a = C2566aa.m646a(context);
                if (m710a != null && m746a != null && m646a != null) {
                    if (!m690c.equals(str) && currentTimeMillis - m746a.m757a(C2585at.f1188a) > 30000) {
                        C2577al.m781a("try to upload crash on network changed.", new Object[0]);
                        C2585at m904a = C2585at.m904a();
                        if (m904a != null) {
                            m904a.m908a(0L);
                        }
                        C2577al.m781a("try to upload userinfo on network changed.", new Object[0]);
                        C2631s.f1488b.m1112b();
                    }
                    return true;
                }
                C2577al.m786d("not inited BC not work", new Object[0]);
                return true;
            }
        }
        return false;
    }
}
