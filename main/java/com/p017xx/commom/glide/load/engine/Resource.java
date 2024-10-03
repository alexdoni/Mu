package com.p017xx.commom.glide.load.engine;

/* loaded from: classes3.dex */
public interface Resource<Z> {
    Z get();

    Class<Z> getResourceClass();

    int getSize();

    void recycle();
}
