package com.p017xx.commom.glide.request.target;

import android.graphics.drawable.Drawable;
import com.p017xx.commom.glide.request.Request;

@Deprecated
/* loaded from: classes3.dex */
public abstract class BaseTarget<Z> implements Target<Z> {
    private Request request;

    @Override // com.p017xx.commom.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public void onLoadCleared(Drawable drawable) {
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public void onLoadFailed(Drawable drawable) {
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
    public void setRequest(Request request) {
        this.request = request;
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public Request getRequest() {
        return this.request;
    }
}
