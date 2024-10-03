package com.p017xx.commom.glide.request.target;

import com.p017xx.commom.glide.util.Util;

@Deprecated
/* loaded from: classes3.dex */
public abstract class SimpleTarget<Z> extends BaseTarget<Z> {
    private final int height;
    private final int width;

    @Override // com.p017xx.commom.glide.request.target.Target
    public void removeCallback(SizeReadyCallback sizeReadyCallback) {
    }

    public SimpleTarget() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public SimpleTarget(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    @Override // com.p017xx.commom.glide.request.target.Target
    public final void getSize(SizeReadyCallback sizeReadyCallback) {
        if (!Util.isValidDimensions(this.width, this.height)) {
            throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + this.width + " and height: " + this.height + ", either provide dimensions in the constructor or call override()");
        }
        sizeReadyCallback.onSizeReady(this.width, this.height);
    }
}
