package com.p017xx.commom.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.p017xx.commom.glide.load.engine.DiskCacheStrategy;
import com.p017xx.commom.glide.load.resource.gif.GifDrawable;
import com.p017xx.commom.glide.manager.ConnectivityMonitor;
import com.p017xx.commom.glide.manager.ConnectivityMonitorFactory;
import com.p017xx.commom.glide.manager.Lifecycle;
import com.p017xx.commom.glide.manager.LifecycleListener;
import com.p017xx.commom.glide.manager.RequestManagerTreeNode;
import com.p017xx.commom.glide.manager.RequestTracker;
import com.p017xx.commom.glide.manager.TargetTracker;
import com.p017xx.commom.glide.request.BaseRequestOptions;
import com.p017xx.commom.glide.request.Request;
import com.p017xx.commom.glide.request.RequestListener;
import com.p017xx.commom.glide.request.RequestOptions;
import com.p017xx.commom.glide.request.target.Target;
import com.p017xx.commom.glide.request.target.ViewTarget;
import com.p017xx.commom.glide.request.transition.Transition;
import com.p017xx.commom.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public class RequestManager implements LifecycleListener, ModelTypes<RequestBuilder<Drawable>> {
    private static final RequestOptions DECODE_TYPE_BITMAP = RequestOptions.decodeTypeOf(Bitmap.class).lock();
    private static final RequestOptions DECODE_TYPE_GIF = RequestOptions.decodeTypeOf(GifDrawable.class).lock();
    private static final RequestOptions DOWNLOAD_ONLY_OPTIONS = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    private final Runnable addSelfToLifecycle;
    private final ConnectivityMonitor connectivityMonitor;
    protected final Context context;
    private final CopyOnWriteArrayList<RequestListener<Object>> defaultRequestListeners;
    protected final Glide glide;
    final Lifecycle lifecycle;
    private final Handler mainHandler;
    private RequestOptions requestOptions;
    private final RequestTracker requestTracker;
    private final TargetTracker targetTracker;
    private final RequestManagerTreeNode treeNode;

    public RequestManager(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
        this(glide, lifecycle, requestManagerTreeNode, new RequestTracker(), glide.getConnectivityMonitorFactory(), context);
    }

    RequestManager(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, RequestTracker requestTracker, ConnectivityMonitorFactory connectivityMonitorFactory, Context context) {
        this.targetTracker = new TargetTracker();
        Runnable runnable = new Runnable() { // from class: com.xx.commom.glide.RequestManager.1
            @Override // java.lang.Runnable
            public void run() {
                RequestManager.this.lifecycle.addListener(RequestManager.this);
            }
        };
        this.addSelfToLifecycle = runnable;
        Handler handler = new Handler(Looper.getMainLooper());
        this.mainHandler = handler;
        this.glide = glide;
        this.lifecycle = lifecycle;
        this.treeNode = requestManagerTreeNode;
        this.requestTracker = requestTracker;
        this.context = context;
        ConnectivityMonitor build = connectivityMonitorFactory.build(context.getApplicationContext(), new RequestManagerConnectivityListener(requestTracker));
        this.connectivityMonitor = build;
        if (Util.isOnBackgroundThread()) {
            handler.post(runnable);
        } else {
            lifecycle.addListener(this);
        }
        lifecycle.addListener(build);
        this.defaultRequestListeners = new CopyOnWriteArrayList<>(glide.getGlideContext().getDefaultRequestListeners());
        setRequestOptions(glide.getGlideContext().getDefaultRequestOptions());
        glide.registerRequestManager(this);
    }

    protected synchronized void setRequestOptions(RequestOptions requestOptions) {
        this.requestOptions = requestOptions.mo1876clone().autoClone();
    }

    private synchronized void updateRequestOptions(RequestOptions requestOptions) {
        this.requestOptions = this.requestOptions.apply(requestOptions);
    }

    public synchronized RequestManager applyDefaultRequestOptions(RequestOptions requestOptions) {
        updateRequestOptions(requestOptions);
        return this;
    }

    public synchronized RequestManager setDefaultRequestOptions(RequestOptions requestOptions) {
        setRequestOptions(requestOptions);
        return this;
    }

    public RequestManager addDefaultRequestListener(RequestListener<Object> requestListener) {
        this.defaultRequestListeners.add(requestListener);
        return this;
    }

    public synchronized boolean isPaused() {
        return this.requestTracker.isPaused();
    }

    public synchronized void pauseRequests() {
        this.requestTracker.pauseRequests();
    }

    public synchronized void pauseAllRequests() {
        this.requestTracker.pauseAllRequests();
    }

    public synchronized void pauseRequestsRecursive() {
        pauseRequests();
        Iterator<RequestManager> it = this.treeNode.getDescendants().iterator();
        while (it.hasNext()) {
            it.next().pauseRequests();
        }
    }

    public synchronized void resumeRequests() {
        this.requestTracker.resumeRequests();
    }

    public synchronized void resumeRequestsRecursive() {
        Util.assertMainThread();
        resumeRequests();
        Iterator<RequestManager> it = this.treeNode.getDescendants().iterator();
        while (it.hasNext()) {
            it.next().resumeRequests();
        }
    }

    @Override // com.p017xx.commom.glide.manager.LifecycleListener
    public synchronized void onStart() {
        resumeRequests();
        this.targetTracker.onStart();
    }

    @Override // com.p017xx.commom.glide.manager.LifecycleListener
    public synchronized void onStop() {
        pauseRequests();
        this.targetTracker.onStop();
    }

    @Override // com.p017xx.commom.glide.manager.LifecycleListener
    public synchronized void onDestroy() {
        this.targetTracker.onDestroy();
        Iterator<Target<?>> it = this.targetTracker.getAll().iterator();
        while (it.hasNext()) {
            clear(it.next());
        }
        this.targetTracker.clear();
        this.requestTracker.clearRequests();
        this.lifecycle.removeListener(this);
        this.lifecycle.removeListener(this.connectivityMonitor);
        this.mainHandler.removeCallbacks(this.addSelfToLifecycle);
        this.glide.unregisterRequestManager(this);
    }

    public RequestBuilder<Bitmap> asBitmap() {
        return m1390as(Bitmap.class).apply((BaseRequestOptions<?>) DECODE_TYPE_BITMAP);
    }

    public RequestBuilder<GifDrawable> asGif() {
        return m1390as(GifDrawable.class).apply((BaseRequestOptions<?>) DECODE_TYPE_GIF);
    }

    public RequestBuilder<Drawable> asDrawable() {
        return m1390as(Drawable.class);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.ModelTypes
    public RequestBuilder<Drawable> load(Bitmap bitmap) {
        return asDrawable().load(bitmap);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.ModelTypes
    public RequestBuilder<Drawable> load(Drawable drawable) {
        return asDrawable().load(drawable);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.ModelTypes
    public RequestBuilder<Drawable> load(String str) {
        return asDrawable().load(str);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.ModelTypes
    public RequestBuilder<Drawable> load(Uri uri) {
        return asDrawable().load(uri);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.ModelTypes
    public RequestBuilder<Drawable> load(File file) {
        return asDrawable().load(file);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.ModelTypes
    public RequestBuilder<Drawable> load(Integer num) {
        return asDrawable().load(num);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.ModelTypes
    @Deprecated
    public RequestBuilder<Drawable> load(URL url) {
        return asDrawable().load(url);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.ModelTypes
    public RequestBuilder<Drawable> load(byte[] bArr) {
        return asDrawable().load(bArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.ModelTypes
    public RequestBuilder<Drawable> load(Object obj) {
        return asDrawable().load(obj);
    }

    public RequestBuilder<File> downloadOnly() {
        return m1390as(File.class).apply((BaseRequestOptions<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    public RequestBuilder<File> download(Object obj) {
        return downloadOnly().load(obj);
    }

    public RequestBuilder<File> asFile() {
        return m1390as(File.class).apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true));
    }

    /* renamed from: as */
    public <ResourceType> RequestBuilder<ResourceType> m1390as(Class<ResourceType> cls) {
        return new RequestBuilder<>(this.glide, this, cls, this.context);
    }

    public void clear(View view) {
        clear(new ClearTarget(view));
    }

    public synchronized void clear(Target<?> target) {
        if (target == null) {
            return;
        }
        untrackOrDelegate(target);
    }

    private void untrackOrDelegate(Target<?> target) {
        if (untrack(target) || this.glide.removeFromManagers(target) || target.getRequest() == null) {
            return;
        }
        Request request = target.getRequest();
        target.setRequest(null);
        request.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean untrack(Target<?> target) {
        Request request = target.getRequest();
        if (request == null) {
            return true;
        }
        if (!this.requestTracker.clearRemoveAndRecycle(request)) {
            return false;
        }
        this.targetTracker.untrack(target);
        target.setRequest(null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void track(Target<?> target, Request request) {
        this.targetTracker.track(target);
        this.requestTracker.runRequest(request);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<RequestListener<Object>> getDefaultRequestListeners() {
        return this.defaultRequestListeners;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized RequestOptions getDefaultRequestOptions() {
        return this.requestOptions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> TransitionOptions<?, T> getDefaultTransitionOptions(Class<T> cls) {
        return this.glide.getGlideContext().getDefaultTransitionOptions(cls);
    }

    public synchronized String toString() {
        return super.toString() + "{tracker=" + this.requestTracker + ", treeNode=" + this.treeNode + "}";
    }

    /* loaded from: classes3.dex */
    private class RequestManagerConnectivityListener implements ConnectivityMonitor.ConnectivityListener {
        private final RequestTracker requestTracker;

        RequestManagerConnectivityListener(RequestTracker requestTracker) {
            this.requestTracker = requestTracker;
        }

        @Override // com.xx.commom.glide.manager.ConnectivityMonitor.ConnectivityListener
        public void onConnectivityChanged(boolean z) {
            if (z) {
                synchronized (RequestManager.this) {
                    this.requestTracker.restartRequests();
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    private static class ClearTarget extends ViewTarget<View, Object> {
        @Override // com.p017xx.commom.glide.request.target.Target
        public void onResourceReady(Object obj, Transition<? super Object> transition) {
        }

        ClearTarget(View view) {
            super(view);
        }
    }
}
