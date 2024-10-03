package com.oversea.ab_firstarea.utils;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public class HandlerUtils implements Runnable {
    private static volatile HandlerUtils handlerUtils;
    private HandlerCallback callback;
    private boolean isStart;
    private int duration = 1000;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /* loaded from: classes.dex */
    public interface HandlerCallback {
        void call();
    }

    public static HandlerUtils getInstance() {
        if (handlerUtils == null) {
            synchronized (HandlerUtils.class) {
                if (handlerUtils == null) {
                    handlerUtils = new HandlerUtils();
                }
            }
        }
        return handlerUtils;
    }

    @Override // java.lang.Runnable
    public void run() {
        HandlerCallback handlerCallback = this.callback;
        if (handlerCallback != null) {
            handlerCallback.call();
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.postDelayed(this, this.duration);
        }
    }

    public void startPolling(int i, HandlerCallback handlerCallback) {
        this.callback = handlerCallback;
        this.duration = i;
        Handler handler = this.mHandler;
        if (handler == null || this.isStart) {
            return;
        }
        this.isStart = true;
        handler.postDelayed(this, i);
    }

    public void removeCallback() {
        Handler handler = this.mHandler;
        if (handler != null) {
            this.isStart = false;
            handler.removeCallbacks(this);
        }
    }
}
