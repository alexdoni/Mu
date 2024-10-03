package com.p017xx.commom.glide.request;

import android.graphics.drawable.Drawable;
import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.engine.GlideException;
import com.p017xx.commom.glide.request.target.SizeReadyCallback;
import com.p017xx.commom.glide.request.target.Target;
import com.p017xx.commom.glide.request.transition.Transition;
import com.p017xx.commom.glide.util.Util;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes3.dex */
public class RequestFutureTarget<R> implements FutureTarget<R>, RequestListener<R> {
    private static final Waiter DEFAULT_WAITER = new Waiter();
    private final boolean assertBackgroundThread;
    private GlideException exception;
    private final int height;
    private boolean isCancelled;
    private boolean loadFailed;
    private Request request;
    private R resource;
    private boolean resultReceived;
    private final Waiter waiter;
    private final int width;

    @Override // com.p017xx.commom.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public void onLoadCleared(Drawable drawable) {
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public void onLoadStarted(Drawable drawable) {
    }

    @Override // com.p017xx.commom.glide.manager.LifecycleListener
    public void onStart() {
    }

    @Override // com.p017xx.commom.glide.manager.LifecycleListener
    public void onStop() {
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public void removeCallback(SizeReadyCallback sizeReadyCallback) {
    }

    public RequestFutureTarget(int i, int i2) {
        this(i, i2, true, DEFAULT_WAITER);
    }

    RequestFutureTarget(int i, int i2, boolean z, Waiter waiter) {
        this.width = i;
        this.height = i2;
        this.assertBackgroundThread = z;
        this.waiter = waiter;
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean cancel(boolean z) {
        Request request;
        if (isDone()) {
            return false;
        }
        this.isCancelled = true;
        this.waiter.notifyAll(this);
        if (z && (request = this.request) != null) {
            request.clear();
            this.request = null;
        }
        return true;
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean isCancelled() {
        return this.isCancelled;
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean isDone() {
        boolean z;
        if (!this.isCancelled && !this.resultReceived) {
            z = this.loadFailed;
        }
        return z;
    }

    @Override // java.util.concurrent.Future
    public R get() throws InterruptedException, ExecutionException {
        try {
            return doGet(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.util.concurrent.Future
    public R get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return doGet(Long.valueOf(timeUnit.toMillis(j)));
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public void getSize(SizeReadyCallback sizeReadyCallback) {
        sizeReadyCallback.onSizeReady(this.width, this.height);
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public synchronized void setRequest(Request request) {
        this.request = request;
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public synchronized Request getRequest() {
        return this.request;
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public synchronized void onLoadFailed(Drawable drawable) {
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public synchronized void onResourceReady(R r, Transition<? super R> transition) {
    }

    private synchronized R doGet(Long l) throws ExecutionException, InterruptedException, TimeoutException {
        if (this.assertBackgroundThread && !isDone()) {
            Util.assertBackgroundThread();
        }
        if (this.isCancelled) {
            throw new CancellationException();
        }
        if (this.loadFailed) {
            throw new ExecutionException(this.exception);
        }
        if (this.resultReceived) {
            return this.resource;
        }
        if (l == null) {
            this.waiter.waitForTimeout(this, 0L);
        } else if (l.longValue() > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            long longValue = l.longValue() + currentTimeMillis;
            while (!isDone() && currentTimeMillis < longValue) {
                this.waiter.waitForTimeout(this, longValue - currentTimeMillis);
                currentTimeMillis = System.currentTimeMillis();
            }
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (this.loadFailed) {
            throw new ExecutionException(this.exception);
        }
        if (this.isCancelled) {
            throw new CancellationException();
        }
        if (!this.resultReceived) {
            throw new TimeoutException();
        }
        return this.resource;
    }

    @Override // com.p017xx.commom.glide.request.RequestListener
    public synchronized boolean onLoadFailed(GlideException glideException, Object obj, Target<R> target, boolean z) {
        this.loadFailed = true;
        this.exception = glideException;
        this.waiter.notifyAll(this);
        return false;
    }

    @Override // com.p017xx.commom.glide.request.RequestListener
    public synchronized boolean onResourceReady(R r, Object obj, Target<R> target, DataSource dataSource, boolean z) {
        this.resultReceived = true;
        this.resource = r;
        this.waiter.notifyAll(this);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Waiter {
        Waiter() {
        }

        void waitForTimeout(Object obj, long j) throws InterruptedException {
            obj.wait(j);
        }

        void notifyAll(Object obj) {
            obj.notifyAll();
        }
    }
}
