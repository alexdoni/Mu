package com.google.androidgamesdk;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Choreographer;

/* loaded from: classes2.dex */
public class ChoreographerCallback implements Choreographer.FrameCallback {
    private static final String LOG_TAG = "ChoreographerCallback";
    private long mCookie;
    private C1106a mLooper;

    /* renamed from: com.google.androidgamesdk.ChoreographerCallback$a */
    /* loaded from: classes2.dex */
    private class C1106a extends Thread {

        /* renamed from: a */
        public Handler f537a;

        private C1106a() {
        }

        /* synthetic */ C1106a(ChoreographerCallback choreographerCallback, byte b) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Log.i(ChoreographerCallback.LOG_TAG, "Starting looper thread");
            Looper.prepare();
            this.f537a = new Handler();
            Looper.loop();
            Log.i(ChoreographerCallback.LOG_TAG, "Terminating looper thread");
        }
    }

    public ChoreographerCallback(long j) {
        this.mCookie = j;
        C1106a c1106a = new C1106a(this, (byte) 0);
        this.mLooper = c1106a;
        c1106a.start();
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        nOnChoreographer(this.mCookie, j);
    }

    public native void nOnChoreographer(long j, long j2);

    public void postFrameCallback() {
        this.mLooper.f537a.post(new Runnable() { // from class: com.google.androidgamesdk.ChoreographerCallback.1
            @Override // java.lang.Runnable
            public final void run() {
                Choreographer.getInstance().postFrameCallback(ChoreographerCallback.this);
            }
        });
    }

    public void postFrameCallbackDelayed(long j) {
        Choreographer.getInstance().postFrameCallbackDelayed(this, j);
    }

    public void terminate() {
        this.mLooper.f537a.getLooper().quit();
    }
}
