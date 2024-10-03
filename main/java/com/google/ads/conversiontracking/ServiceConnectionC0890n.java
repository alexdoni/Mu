package com.google.ads.conversiontracking;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.google.ads.conversiontracking.n */
/* loaded from: classes.dex */
public class ServiceConnectionC0890n implements ServiceConnection {

    /* renamed from: a */
    boolean f497a = false;

    /* renamed from: b */
    private final BlockingQueue<IBinder> f498b = new LinkedBlockingQueue();

    /* renamed from: a */
    public IBinder m238a() throws InterruptedException {
        if (this.f497a) {
            throw new IllegalStateException();
        }
        this.f497a = true;
        return this.f498b.take();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.f498b.put(iBinder);
        } catch (InterruptedException unused) {
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }
}
