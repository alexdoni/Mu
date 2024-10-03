package com.p017xx.commom.glide.util;

import android.view.View;
import com.p017xx.commom.glide.ListPreloader;
import com.p017xx.commom.glide.request.target.SizeReadyCallback;
import com.p017xx.commom.glide.request.target.ViewTarget;
import com.p017xx.commom.glide.request.transition.Transition;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class ViewPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T>, SizeReadyCallback {
    private int[] size;
    private SizeViewTarget viewTarget;

    public ViewPreloadSizeProvider() {
    }

    public ViewPreloadSizeProvider(View view) {
        this.viewTarget = new SizeViewTarget(view, this);
    }

    @Override // com.xx.commom.glide.ListPreloader.PreloadSizeProvider
    public int[] getPreloadSize(T t, int i, int i2) {
        int[] iArr = this.size;
        if (iArr == null) {
            return null;
        }
        return Arrays.copyOf(iArr, iArr.length);
    }

    @Override // com.p017xx.commom.glide.request.target.SizeReadyCallback
    public void onSizeReady(int i, int i2) {
        this.size = new int[]{i, i2};
        this.viewTarget = null;
    }

    public void setView(View view) {
        if (this.size == null && this.viewTarget == null) {
            this.viewTarget = new SizeViewTarget(view, this);
        }
    }

    /* loaded from: classes3.dex */
    private static final class SizeViewTarget extends ViewTarget<View, Object> {
        @Override // com.p017xx.commom.glide.request.target.Target
        public void onResourceReady(Object obj, Transition<? super Object> transition) {
        }

        SizeViewTarget(View view, SizeReadyCallback sizeReadyCallback) {
            super(view);
            getSize(sizeReadyCallback);
        }
    }
}
