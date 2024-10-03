package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/* loaded from: classes3.dex */
public class CircleCrop extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f1924ID = "com.xx.commom.glide.load.resource.bitmap.CircleCrop.1";
    private static final byte[] ID_BYTES = f1924ID.getBytes(CHARSET);
    private static final int VERSION = 1;

    @Override // com.p017xx.commom.glide.load.Key
    public int hashCode() {
        return 1512443742;
    }

    @Override // com.p017xx.commom.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return TransformationUtils.circleCrop(bitmapPool, bitmap, i, i2);
    }

    @Override // com.p017xx.commom.glide.load.Key
    public boolean equals(Object obj) {
        return obj instanceof CircleCrop;
    }

    @Override // com.p017xx.commom.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
