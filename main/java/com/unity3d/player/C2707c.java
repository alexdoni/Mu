package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.c */
/* loaded from: classes3.dex */
class C2707c {

    /* renamed from: b */
    protected InterfaceC2710f f1806b;

    /* renamed from: e */
    protected String f1809e;

    /* renamed from: a */
    protected C2720p f1805a = null;

    /* renamed from: c */
    protected Context f1807c = null;

    /* renamed from: d */
    protected String f1808d = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2707c(String str, InterfaceC2710f interfaceC2710f) {
        this.f1809e = str;
        this.f1806b = interfaceC2710f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportError(String str) {
        InterfaceC2710f interfaceC2710f = this.f1806b;
        if (interfaceC2710f != null) {
            interfaceC2710f.reportError(this.f1809e + " Error [" + this.f1808d + "]", str);
            return;
        }
        C2711g.Log(6, this.f1809e + " Error [" + this.f1808d + "]: " + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void runOnUiThread(Runnable runnable) {
        Context context = this.f1807c;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
            return;
        }
        C2711g.Log(5, "Not running " + this.f1809e + " from an Activity; Ignoring execution request...");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean runOnUiThreadWithSync(final Runnable runnable) {
        boolean z = true;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return true;
        }
        final Semaphore semaphore = new Semaphore(0);
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.c.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        C2707c.this.reportError("Exception unloading Google VR on UI Thread. " + e.getLocalizedMessage());
                    }
                } finally {
                    semaphore.release();
                }
            }
        });
        try {
            if (!semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                reportError("Timeout waiting for vr state change!");
                z = false;
            }
            return z;
        } catch (InterruptedException e) {
            reportError("Interrupted while trying to acquire sync lock. " + e.getLocalizedMessage());
            return false;
        }
    }
}
