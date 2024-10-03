package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.bf */
/* loaded from: classes3.dex */
public final class RunnableC2598bf implements Runnable {

    /* renamed from: a */
    final Handler f1301a;

    /* renamed from: d */
    long f1304d;

    /* renamed from: e */
    private final String f1305e;

    /* renamed from: f */
    private final List<C2593ba> f1306f = new LinkedList();

    /* renamed from: b */
    long f1302b = 5000;

    /* renamed from: g */
    private final long f1307g = 5000;

    /* renamed from: c */
    boolean f1303c = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2598bf(Handler handler, String str) {
        this.f1301a = handler;
        this.f1305e = str;
    }

    /* renamed from: a */
    public final boolean m981a() {
        return !this.f1303c && SystemClock.uptimeMillis() >= this.f1304d + this.f1302b;
    }

    /* renamed from: e */
    private Thread m980e() {
        return this.f1301a.getLooper().getThread();
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1303c = true;
        this.f1302b = this.f1307g;
    }

    /* renamed from: b */
    public final long m982b() {
        return SystemClock.uptimeMillis() - this.f1304d;
    }

    /* renamed from: c */
    public final List<C2593ba> m983c() {
        ArrayList arrayList;
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.f1306f) {
            arrayList = new ArrayList(this.f1306f.size());
            for (int i = 0; i < this.f1306f.size(); i++) {
                C2593ba c2593ba = this.f1306f.get(i);
                if (!c2593ba.f1282e && currentTimeMillis - c2593ba.f1279b < 200000) {
                    arrayList.add(c2593ba);
                    c2593ba.f1282e = true;
                }
            }
        }
        return arrayList;
    }

    /* renamed from: d */
    public final void m984d() {
        StringBuilder sb = new StringBuilder(1024);
        long nanoTime = System.nanoTime();
        try {
            StackTraceElement[] stackTrace = m980e().getStackTrace();
            if (stackTrace.length == 0) {
                sb.append("Thread does not have stack trace.\n");
            } else {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    sb.append(stackTraceElement);
                    sb.append("\n");
                }
            }
        } catch (SecurityException e) {
            sb.append("getStackTrace() encountered:\n");
            sb.append(e.getMessage());
            sb.append("\n");
            C2577al.m782a(e);
        }
        long nanoTime2 = System.nanoTime();
        C2593ba c2593ba = new C2593ba(sb.toString(), System.currentTimeMillis());
        c2593ba.f1281d = nanoTime2 - nanoTime;
        String name = m980e().getName();
        if (name == null) {
            name = "";
        }
        c2593ba.f1278a = name;
        synchronized (this.f1306f) {
            while (this.f1306f.size() >= 32) {
                this.f1306f.remove(0);
            }
            this.f1306f.add(c2593ba);
        }
    }
}
