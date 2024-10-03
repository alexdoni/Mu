package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import com.unity3d.player.SurfaceHolderCallbackC2721q;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unity3d.player.r */
/* loaded from: classes3.dex */
public final class C2722r {

    /* renamed from: a */
    private UnityPlayer f1884a;

    /* renamed from: c */
    private a f1886c;

    /* renamed from: b */
    private Context f1885b = null;

    /* renamed from: d */
    private final Semaphore f1887d = new Semaphore(0);

    /* renamed from: e */
    private final Lock f1888e = new ReentrantLock();

    /* renamed from: f */
    private SurfaceHolderCallbackC2721q f1889f = null;

    /* renamed from: g */
    private int f1890g = 2;

    /* renamed from: h */
    private boolean f1891h = false;

    /* renamed from: i */
    private boolean f1892i = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.unity3d.player.r$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public final class AnonymousClass1 implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f1893a;

        /* renamed from: b */
        final /* synthetic */ int f1894b;

        /* renamed from: c */
        final /* synthetic */ int f1895c;

        /* renamed from: d */
        final /* synthetic */ int f1896d;

        /* renamed from: e */
        final /* synthetic */ boolean f1897e;

        /* renamed from: f */
        final /* synthetic */ long f1898f;

        /* renamed from: g */
        final /* synthetic */ long f1899g;

        AnonymousClass1(String str, int i, int i2, int i3, boolean z, long j, long j2) {
            this.f1893a = str;
            this.f1894b = i;
            this.f1895c = i2;
            this.f1896d = i3;
            this.f1897e = z;
            this.f1898f = j;
            this.f1899g = j2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (C2722r.this.f1889f != null) {
                C2711g.Log(5, "Video already playing");
                C2722r.this.f1890g = 2;
                C2722r.this.f1887d.release();
            } else {
                C2722r.this.f1889f = new SurfaceHolderCallbackC2721q(C2722r.this.f1885b, this.f1893a, this.f1894b, this.f1895c, this.f1896d, this.f1897e, this.f1898f, this.f1899g, new SurfaceHolderCallbackC2721q.a() { // from class: com.unity3d.player.r.1.1
                    @Override // com.unity3d.player.SurfaceHolderCallbackC2721q.a
                    /* renamed from: a */
                    public final void mo1373a(int i) {
                        C2722r.this.f1888e.lock();
                        C2722r.this.f1890g = i;
                        if (i == 3 && C2722r.this.f1892i) {
                            C2722r.this.runOnUiThread(new Runnable() { // from class: com.unity3d.player.r.1.1.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    C2722r.this.m1381d();
                                    C2722r.this.f1884a.resume();
                                }
                            });
                        }
                        if (i != 0) {
                            C2722r.this.f1887d.release();
                        }
                        C2722r.this.f1888e.unlock();
                    }
                });
                if (C2722r.this.f1889f != null) {
                    C2722r.this.f1884a.addView(C2722r.this.f1889f);
                }
            }
        }
    }

    /* renamed from: com.unity3d.player.r$a */
    /* loaded from: classes3.dex */
    public interface a {
        /* renamed from: a */
        void mo1273a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2722r(UnityPlayer unityPlayer) {
        this.f1884a = null;
        this.f1884a = unityPlayer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m1381d() {
        SurfaceHolderCallbackC2721q surfaceHolderCallbackC2721q = this.f1889f;
        if (surfaceHolderCallbackC2721q != null) {
            this.f1884a.removeViewFromPlayer(surfaceHolderCallbackC2721q);
            this.f1892i = false;
            this.f1889f.destroyPlayer();
            this.f1889f = null;
            a aVar = this.f1886c;
            if (aVar != null) {
                aVar.mo1273a();
            }
        }
    }

    /* renamed from: h */
    static /* synthetic */ boolean m1385h(C2722r c2722r) {
        c2722r.f1892i = true;
        return true;
    }

    /* renamed from: a */
    public final void m1386a() {
        this.f1888e.lock();
        SurfaceHolderCallbackC2721q surfaceHolderCallbackC2721q = this.f1889f;
        if (surfaceHolderCallbackC2721q != null) {
            if (this.f1890g == 0) {
                surfaceHolderCallbackC2721q.CancelOnPrepare();
            } else if (this.f1892i) {
                boolean m1372a = surfaceHolderCallbackC2721q.m1372a();
                this.f1891h = m1372a;
                if (!m1372a) {
                    this.f1889f.pause();
                }
            }
        }
        this.f1888e.unlock();
    }

    /* renamed from: a */
    public final boolean m1387a(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, a aVar) {
        this.f1888e.lock();
        this.f1886c = aVar;
        this.f1885b = context;
        this.f1887d.drainPermits();
        this.f1890g = 2;
        runOnUiThread(new AnonymousClass1(str, i, i2, i3, z, j, j2));
        boolean z2 = false;
        try {
            this.f1888e.unlock();
            this.f1887d.acquire();
            this.f1888e.lock();
            if (this.f1890g != 2) {
                z2 = true;
            }
        } catch (InterruptedException unused) {
        }
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.r.2
            @Override // java.lang.Runnable
            public final void run() {
                C2722r.this.f1884a.pause();
            }
        });
        runOnUiThread((!z2 || this.f1890g == 3) ? new Runnable() { // from class: com.unity3d.player.r.4
            @Override // java.lang.Runnable
            public final void run() {
                C2722r.this.m1381d();
                C2722r.this.f1884a.resume();
            }
        } : new Runnable() { // from class: com.unity3d.player.r.3
            @Override // java.lang.Runnable
            public final void run() {
                if (C2722r.this.f1889f != null) {
                    C2722r.this.f1884a.addViewToPlayer(C2722r.this.f1889f, true);
                    C2722r.m1385h(C2722r.this);
                    C2722r.this.f1889f.requestFocus();
                }
            }
        });
        this.f1888e.unlock();
        return z2;
    }

    /* renamed from: b */
    public final void m1388b() {
        this.f1888e.lock();
        SurfaceHolderCallbackC2721q surfaceHolderCallbackC2721q = this.f1889f;
        if (surfaceHolderCallbackC2721q != null && this.f1892i && !this.f1891h) {
            surfaceHolderCallbackC2721q.start();
        }
        this.f1888e.unlock();
    }

    /* renamed from: c */
    public final void m1389c() {
        this.f1888e.lock();
        SurfaceHolderCallbackC2721q surfaceHolderCallbackC2721q = this.f1889f;
        if (surfaceHolderCallbackC2721q != null) {
            surfaceHolderCallbackC2721q.updateVideoLayout();
        }
        this.f1888e.unlock();
    }

    protected final void runOnUiThread(Runnable runnable) {
        Context context = this.f1885b;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        } else {
            C2711g.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}
