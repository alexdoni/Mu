package com.p017xx.commom.glide.load.resource.transcode;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.load.resource.bitmap.BitmapResource;
import com.p017xx.commom.glide.load.resource.gif.GifDrawable;

/* loaded from: classes3.dex */
public final class DrawableBytesTranscoder implements ResourceTranscoder<Drawable, byte[]> {
    private final ResourceTranscoder<Bitmap, byte[]> bitmapBytesTranscoder;
    private final BitmapPool bitmapPool;
    private final ResourceTranscoder<GifDrawable, byte[]> gifDrawableBytesTranscoder;

    /* JADX WARN: Multi-variable type inference failed */
    private static Resource<GifDrawable> toGifDrawableResource(Resource<Drawable> resource) {
        return resource;
    }

    public DrawableBytesTranscoder(BitmapPool bitmapPool, ResourceTranscoder<Bitmap, byte[]> resourceTranscoder, ResourceTranscoder<GifDrawable, byte[]> resourceTranscoder2) {
        this.bitmapPool = bitmapPool;
        this.bitmapBytesTranscoder = resourceTranscoder;
        this.gifDrawableBytesTranscoder = resourceTranscoder2;
    }

    @Override // com.p017xx.commom.glide.load.resource.transcode.ResourceTranscoder
    public Resource<byte[]> transcode(Resource<Drawable> resource, Options options) {
        Drawable drawable = resource.get();
        if (drawable instanceof BitmapDrawable) {
            return this.bitmapBytesTranscoder.transcode(BitmapResource.obtain(((BitmapDrawable) drawable).getBitmap(), this.bitmapPool), options);
        }
        if (drawable instanceof GifDrawable) {
            return this.gifDrawableBytesTranscoder.transcode(toGifDrawableResource(resource), options);
        }
        return null;
    }
}
