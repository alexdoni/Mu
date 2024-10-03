package com.p017xx.commom.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;

/* loaded from: classes3.dex */
public class BitmapPoolAdapter implements BitmapPool {
    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool
    public void clearMemory() {
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool
    public long getMaxSize() {
        return 0L;
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool
    public void setSizeMultiplier(float f) {
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool
    public void trimMemory(int i) {
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool
    public void put(Bitmap bitmap) {
        bitmap.recycle();
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool
    public Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        return get(i, i2, config);
    }
}
