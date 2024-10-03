package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.p017xx.commom.glide.load.EncodeStrategy;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceEncoder;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.File;

/* loaded from: classes3.dex */
public class BitmapDrawableEncoder implements ResourceEncoder<BitmapDrawable> {
    private final BitmapPool bitmapPool;
    private final ResourceEncoder<Bitmap> encoder;

    public BitmapDrawableEncoder(BitmapPool bitmapPool, ResourceEncoder<Bitmap> resourceEncoder) {
        this.bitmapPool = bitmapPool;
        this.encoder = resourceEncoder;
    }

    @Override // com.p017xx.commom.glide.load.Encoder
    public boolean encode(Resource<BitmapDrawable> resource, File file, Options options) {
        return this.encoder.encode(new BitmapResource(resource.get().getBitmap(), this.bitmapPool), file, options);
    }

    @Override // com.p017xx.commom.glide.load.ResourceEncoder
    public EncodeStrategy getEncodeStrategy(Options options) {
        return this.encoder.getEncodeStrategy(options);
    }
}
