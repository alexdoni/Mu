package com.p017xx.commom.glide.load.resource.gif;

import android.graphics.Bitmap;
import com.p017xx.commom.glide.gifdecoder.GifDecoder;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceDecoder;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.load.resource.bitmap.BitmapResource;

/* loaded from: classes3.dex */
public final class GifFrameResourceDecoder implements ResourceDecoder<GifDecoder, Bitmap> {
    private final BitmapPool bitmapPool;

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public boolean handles(GifDecoder gifDecoder, Options options) {
        return true;
    }

    public GifFrameResourceDecoder(BitmapPool bitmapPool) {
        this.bitmapPool = bitmapPool;
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(GifDecoder gifDecoder, int i, int i2, Options options) {
        return BitmapResource.obtain(gifDecoder.getNextFrame(), this.bitmapPool);
    }
}
