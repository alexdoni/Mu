package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/* loaded from: classes3.dex */
public class FitCenter extends BitmapTransformation {

    /* renamed from: ID */
    private static final String f1926ID = "com.xx.commom.glide.load.resource.bitmap.FitCenter";
    private static final byte[] ID_BYTES = f1926ID.getBytes(CHARSET);

    @Override // com.p017xx.commom.glide.load.Key
    public int hashCode() {
        return 2117303179;
    }

    @Override // com.p017xx.commom.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return TransformationUtils.fitCenter(bitmapPool, bitmap, i, i2);
    }

    @Override // com.p017xx.commom.glide.load.Key
    public boolean equals(Object obj) {
        return obj instanceof FitCenter;
    }

    @Override // com.p017xx.commom.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
