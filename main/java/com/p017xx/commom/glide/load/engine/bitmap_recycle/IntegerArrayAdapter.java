package com.p017xx.commom.glide.load.engine.bitmap_recycle;

/* loaded from: classes3.dex */
public final class IntegerArrayAdapter implements ArrayAdapterInterface<int[]> {
    private static final String TAG = "IntegerArrayPool";

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int getElementSizeInBytes() {
        return 4;
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public String getTag() {
        return TAG;
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int getArrayLength(int[] iArr) {
        return iArr.length;
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int[] newArray(int i) {
        return new int[i];
    }
}
