package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceDecoder;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.load.resource.drawable.ResourceDrawableDecoder;

/* loaded from: classes3.dex */
public class ResourceBitmapDecoder implements ResourceDecoder<Uri, Bitmap> {
    private final BitmapPool bitmapPool;
    private final ResourceDrawableDecoder drawableDecoder;

    public ResourceBitmapDecoder(ResourceDrawableDecoder resourceDrawableDecoder, BitmapPool bitmapPool) {
        this.drawableDecoder = resourceDrawableDecoder;
        this.bitmapPool = bitmapPool;
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public boolean handles(Uri uri, Options options) {
        return "android.resource".equals(uri.getScheme());
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(Uri uri, int i, int i2, Options options) {
        Resource<Drawable> decode = this.drawableDecoder.decode(uri, i, i2, options);
        if (decode == null) {
            return null;
        }
        return DrawableToBitmapConverter.convert(this.bitmapPool, decode.get(), i, i2);
    }
}
