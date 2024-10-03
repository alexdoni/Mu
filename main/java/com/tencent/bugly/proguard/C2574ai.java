package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ai */
/* loaded from: classes3.dex */
public final class C2574ai {

    /* renamed from: b */
    private static C2574ai f1082b;

    /* renamed from: a */
    public InterfaceC2573ah f1083a;

    /* renamed from: d */
    private final Context f1085d;

    /* renamed from: f */
    private long f1087f;

    /* renamed from: g */
    private long f1088g;

    /* renamed from: e */
    private Map<Integer, Long> f1086e = new HashMap();

    /* renamed from: h */
    private LinkedBlockingQueue<Runnable> f1089h = new LinkedBlockingQueue<>();

    /* renamed from: i */
    private LinkedBlockingQueue<Runnable> f1090i = new LinkedBlockingQueue<>();

    /* renamed from: j */
    private final Object f1091j = new Object();

    /* renamed from: k */
    private long f1092k = 0;

    /* renamed from: l */
    private int f1093l = 0;

    /* renamed from: c */
    private final C2635w f1084c = C2635w.m1154a();

    /* renamed from: b */
    static /* synthetic */ int m755b(C2574ai c2574ai) {
        int i = c2574ai.f1093l - 1;
        c2574ai.f1093l = i;
        return i;
    }

    private C2574ai(Context context) {
        this.f1085d = context;
    }

    /* renamed from: a */
    public static synchronized C2574ai m747a(Context context) {
        C2574ai c2574ai;
        synchronized (C2574ai.class) {
            if (f1082b == null) {
                f1082b = new C2574ai(context);
            }
            c2574ai = f1082b;
        }
        return c2574ai;
    }

    /* renamed from: a */
    public static synchronized C2574ai m746a() {
        C2574ai c2574ai;
        synchronized (C2574ai.class) {
            c2574ai = f1082b;
        }
        return c2574ai;
    }

    /* renamed from: a */
    public final void m760a(int i, C2609bq c2609bq, String str, String str2, InterfaceC2573ah interfaceC2573ah, long j, boolean z) {
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            m752a(new RunnableC2575aj(this.f1085d, i, c2609bq.f1368g, C2570ae.m729a((Object) c2609bq), str, str2, interfaceC2573ah, z), true, true, j);
        } catch (Throwable th2) {
            th = th2;
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m749a(int i, int i2, byte[] bArr, String str, String str2, InterfaceC2573ah interfaceC2573ah, boolean z) {
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            m752a(new RunnableC2575aj(this.f1085d, i, i2, bArr, str, str2, interfaceC2573ah, 0, 0, false), z, false, 0L);
        } catch (Throwable th2) {
            th = th2;
            if (C2577al.m782a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m761a(int i, C2609bq c2609bq, String str, String str2, InterfaceC2573ah interfaceC2573ah, boolean z) {
        m749a(i, c2609bq.f1368g, C2570ae.m729a((Object) c2609bq), str, str2, interfaceC2573ah, z);
    }

    /* renamed from: a */
    public final long m758a(boolean z) {
        long j;
        long m836b = C2581ap.m836b();
        int i = z ? 5 : 3;
        List<C2637y> m1171a = this.f1084c.m1171a(i);
        if (m1171a != null && m1171a.size() > 0) {
            j = 0;
            try {
                C2637y c2637y = m1171a.get(0);
                if (c2637y.f1549e >= m836b) {
                    j = C2581ap.m850d(c2637y.f1551g);
                    if (i == 3) {
                        this.f1087f = j;
                    } else {
                        this.f1088g = j;
                    }
                    m1171a.remove(c2637y);
                }
            } catch (Throwable th) {
                C2577al.m782a(th);
            }
            if (m1171a.size() > 0) {
                this.f1084c.m1173a(m1171a);
            }
        } else {
            j = z ? this.f1088g : this.f1087f;
        }
        C2577al.m785c("[UploadManager] Local network consume: %d KB", Long.valueOf(j / 1024));
        return j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final synchronized void m762a(long j, boolean z) {
        int i = z ? 5 : 3;
        C2637y c2637y = new C2637y();
        c2637y.f1546b = i;
        c2637y.f1549e = C2581ap.m836b();
        c2637y.f1547c = "";
        c2637y.f1548d = "";
        c2637y.f1551g = C2581ap.m849c(j);
        this.f1084c.m1176b(i);
        this.f1084c.m1175a(c2637y);
        if (z) {
            this.f1088g = j;
        } else {
            this.f1087f = j;
        }
        C2577al.m785c("[UploadManager] Network total consume: %d KB", Long.valueOf(j / 1024));
    }

    /* renamed from: a */
    public final synchronized void m759a(int i, long j) {
        if (i < 0) {
            C2577al.m787e("[UploadManager] Unknown uploading ID: %d", Integer.valueOf(i));
            return;
        }
        this.f1086e.put(Integer.valueOf(i), Long.valueOf(j));
        C2637y c2637y = new C2637y();
        c2637y.f1546b = i;
        c2637y.f1549e = j;
        c2637y.f1547c = "";
        c2637y.f1548d = "";
        c2637y.f1551g = new byte[0];
        this.f1084c.m1176b(i);
        this.f1084c.m1175a(c2637y);
        C2577al.m785c("[UploadManager] Uploading(ID:%d) time: %s", Integer.valueOf(i), C2581ap.m818a(j));
    }

    /* renamed from: a */
    public final synchronized long m757a(int i) {
        if (i >= 0) {
            Long l = this.f1086e.get(Integer.valueOf(i));
            if (l != null) {
                return l.longValue();
            }
        } else {
            C2577al.m787e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i));
        }
        return 0L;
    }

    /* renamed from: b */
    public final boolean m763b(int i) {
        if (C2628p.f1469c) {
            C2577al.m785c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() - m757a(i);
        C2577al.m785c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", Long.valueOf(currentTimeMillis / 1000), Integer.valueOf(i));
        if (currentTimeMillis >= 30000) {
            return true;
        }
        C2577al.m781a("[UploadManager] Data only be uploaded once in %d seconds.", 30L);
        return false;
    }

    /* renamed from: b */
    private void m756b() {
        C2576ak m772a = C2576ak.m772a();
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        final LinkedBlockingQueue linkedBlockingQueue2 = new LinkedBlockingQueue();
        synchronized (this.f1091j) {
            C2577al.m785c("[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            int size = this.f1089h.size();
            final int size2 = this.f1090i.size();
            if (size == 0 && size2 == 0) {
                C2577al.m785c("[UploadManager] There is no upload task in queue.", new Object[0]);
                return;
            }
            if (m772a == null || !m772a.m777c()) {
                size2 = 0;
            }
            m753a(this.f1089h, linkedBlockingQueue, size);
            m753a(this.f1090i, linkedBlockingQueue2, size2);
            m750a(size, linkedBlockingQueue);
            if (size2 > 0) {
                C2577al.m785c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(size2), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            }
            C2576ak m772a2 = C2576ak.m772a();
            if (m772a2 != null) {
                m772a2.m774a(new Runnable() { // from class: com.tencent.bugly.proguard.ai.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        Runnable runnable;
                        for (int i = 0; i < size2 && (runnable = (Runnable) linkedBlockingQueue2.poll()) != null; i++) {
                            runnable.run();
                        }
                    }
                });
            }
        }
    }

    /* renamed from: a */
    private static void m753a(LinkedBlockingQueue<Runnable> linkedBlockingQueue, LinkedBlockingQueue<Runnable> linkedBlockingQueue2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            Runnable peek = linkedBlockingQueue.peek();
            if (peek == null) {
                return;
            }
            try {
                linkedBlockingQueue2.put(peek);
                linkedBlockingQueue.poll();
            } catch (Throwable th) {
                C2577al.m787e("[UploadManager] Failed to add upload task to temp urgent queue: %s", th.getMessage());
            }
        }
    }

    /* renamed from: a */
    private void m750a(int i, LinkedBlockingQueue<Runnable> linkedBlockingQueue) {
        C2576ak m772a = C2576ak.m772a();
        if (i > 0) {
            C2577al.m785c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(i), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        }
        for (int i2 = 0; i2 < i; i2++) {
            final Runnable poll = linkedBlockingQueue.poll();
            if (poll == null) {
                return;
            }
            synchronized (this.f1091j) {
                if (this.f1093l >= 2 && m772a != null) {
                    m772a.m774a(poll);
                } else {
                    C2577al.m781a("[UploadManager] Create and start a new thread to execute a upload task: %s", "BUGLY_ASYNC_UPLOAD");
                    if (C2581ap.m824a(new Runnable() { // from class: com.tencent.bugly.proguard.ai.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            poll.run();
                            synchronized (C2574ai.this.f1091j) {
                                C2574ai.m755b(C2574ai.this);
                            }
                        }
                    }, "BUGLY_ASYNC_UPLOAD") != null) {
                        synchronized (this.f1091j) {
                            this.f1093l++;
                        }
                    } else {
                        C2577al.m786d("[UploadManager] Failed to start a thread to execute asynchronous upload task,will try again next time.", new Object[0]);
                        m754a(poll, true);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m754a(Runnable runnable, boolean z) {
        if (runnable == null) {
            C2577al.m781a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            C2577al.m785c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.f1091j) {
                if (z) {
                    this.f1089h.put(runnable);
                } else {
                    this.f1090i.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            C2577al.m787e("[UploadManager] Failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    private void m751a(Runnable runnable, long j) {
        if (runnable == null) {
            C2577al.m786d("[UploadManager] Upload task should not be null", new Object[0]);
            return;
        }
        C2577al.m785c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread m824a = C2581ap.m824a(runnable, "BUGLY_SYNC_UPLOAD");
        if (m824a == null) {
            C2577al.m787e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
            m754a(runnable, true);
            return;
        }
        try {
            m824a.join(j);
        } catch (Throwable th) {
            C2577al.m787e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", th.getMessage());
            m754a(runnable, true);
            m756b();
        }
    }

    /* renamed from: a */
    private void m752a(Runnable runnable, boolean z, boolean z2, long j) {
        C2577al.m785c("[UploadManager] Add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (z2) {
            m751a(runnable, j);
        } else {
            m754a(runnable, z);
            m756b();
        }
    }
}
