package com.p017xx.commom.glide.load.engine.bitmap_recycle;

/* loaded from: classes3.dex */
public final class ByteArrayAdapter implements ArrayAdapterInterface<byte[]> {
    private static final String TAG = "ByteArrayPool";

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int getElementSizeInBytes() {
        return 1;
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public String getTag() {
        return TAG;
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int getArrayLength(byte[] bArr) {
        return bArr.length;
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public byte[] newArray(int i) {
        return new byte[i];
    }
}
