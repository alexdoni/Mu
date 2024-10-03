package com.tencent.bugly.proguard;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ak */
/* loaded from: classes3.dex */
public final class C2576ak {

    /* renamed from: a */
    private static final AtomicInteger f1118a = new AtomicInteger(1);

    /* renamed from: b */
    private static C2576ak f1119b;

    /* renamed from: c */
    private ScheduledExecutorService f1120c;

    protected C2576ak() {
        this.f1120c = null;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(3, new ThreadFactory() { // from class: com.tencent.bugly.proguard.ak.1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("BuglyThread-" + C2576ak.f1118a.getAndIncrement());
                return thread;
            }
        });
        this.f1120c = newScheduledThreadPool;
        if (newScheduledThreadPool == null || newScheduledThreadPool.isShutdown()) {
            C2577al.m786d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }

    /* renamed from: a */
    public static synchronized C2576ak m772a() {
        C2576ak c2576ak;
        synchronized (C2576ak.class) {
            if (f1119b == null) {
                f1119b = new C2576ak();
            }
            c2576ak = f1119b;
        }
        return c2576ak;
    }

    /* renamed from: a */
    public final synchronized boolean m775a(Runnable runnable, long j) {
        if (!m777c()) {
            C2577al.m786d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            return false;
        }
        if (j <= 0) {
            j = 0;
        }
        C2577al.m785c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", Long.valueOf(j), runnable.getClass().getName());
        try {
            this.f1120c.schedule(runnable, j, TimeUnit.MILLISECONDS);
            return true;
        } catch (Throwable th) {
            if (C2628p.f1469c) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* renamed from: a */
    public final synchronized boolean m774a(Runnable runnable) {
        if (!m777c()) {
            C2577al.m786d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            return false;
        }
        if (runnable == null) {
            C2577al.m786d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            return false;
        }
        C2577al.m785c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
        try {
            this.f1120c.execute(runnable);
            return true;
        } catch (Throwable th) {
            if (C2628p.f1469c) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* renamed from: b */
    public final synchronized void m776b() {
        ScheduledExecutorService scheduledExecutorService = this.f1120c;
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            C2577al.m785c("[AsyncTaskHandler] Close async handler.", new Object[0]);
            this.f1120c.shutdownNow();
        }
    }

    /* renamed from: c */
    public final synchronized boolean m777c() {
        ScheduledExecutorService scheduledExecutorService = this.f1120c;
        if (scheduledExecutorService != null) {
            if (!scheduledExecutorService.isShutdown()) {
                return true;
            }
        }
        return false;
    }
}
