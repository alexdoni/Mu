package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.p017xx.commom.glide.load.engine.Initializable;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.util.Preconditions;
import com.p017xx.commom.glide.util.Util;

/* loaded from: classes3.dex */
public class BitmapResource implements Resource<Bitmap>, Initializable {
    private final Bitmap bitmap;
    private final BitmapPool bitmapPool;

    public static BitmapResource obtain(Bitmap bitmap, BitmapPool bitmapPool) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapResource(bitmap, bitmapPool);
    }

    public BitmapResource(Bitmap bitmap, BitmapPool bitmapPool) {
        this.bitmap = (Bitmap) Preconditions.checkNotNull(bitmap, "Bitmap must not be null");
        this.bitmapPool = (BitmapPool) Preconditions.checkNotNull(bitmapPool, "BitmapPool must not be null");
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public Class<Bitmap> getResourceClass() {
        return Bitmap.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.load.engine.Resource
    public Bitmap get() {
        return this.bitmap;
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public int getSize() {
        return Util.getBitmapByteSize(this.bitmap);
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public void recycle() {
        this.bitmapPool.put(this.bitmap);
    }

    @Override // com.p017xx.commom.glide.load.engine.Initializable
    public void initialize() {
        this.bitmap.prepareToDraw();
    }
}
