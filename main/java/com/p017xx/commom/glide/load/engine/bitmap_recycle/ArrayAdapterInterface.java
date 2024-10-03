package com.p017xx.commom.glide.load.engine.bitmap_recycle;

/* loaded from: classes3.dex */
interface ArrayAdapterInterface<T> {
    int getArrayLength(T t);

    int getElementSizeInBytes();

    String getTag();

    T newArray(int i);
}
