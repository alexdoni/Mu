package com.p017xx.commom.glide.request.target;

import android.graphics.drawable.Drawable;
import com.p017xx.commom.glide.manager.LifecycleListener;
import com.p017xx.commom.glide.request.Request;
import com.p017xx.commom.glide.request.transition.Transition;

/* loaded from: classes3.dex */
public interface Target<R> extends LifecycleListener {
    public static final int SIZE_ORIGINAL = Integer.MIN_VALUE;

    Request getRequest();

    void getSize(SizeReadyCallback sizeReadyCallback);

    void onLoadCleared(Drawable drawable);

    void onLoadFailed(Drawable drawable);

    void onLoadStarted(Drawable drawable);

    void onResourceReady(R r, Transition<? super R> transition);

    void removeCallback(SizeReadyCallback sizeReadyCallback);

    void setRequest(Request request);
}
