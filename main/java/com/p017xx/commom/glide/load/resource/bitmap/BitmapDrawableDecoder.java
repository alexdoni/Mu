package com.p017xx.commom.glide.load.resource.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceDecoder;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.util.Preconditions;
import java.io.IOException;

/* loaded from: classes3.dex */
public class BitmapDrawableDecoder<DataType> implements ResourceDecoder<DataType, BitmapDrawable> {
    private final ResourceDecoder<DataType, Bitmap> decoder;
    private final Resources resources;

    public BitmapDrawableDecoder(Context context, ResourceDecoder<DataType, Bitmap> resourceDecoder) {
        this(context.getResources(), resourceDecoder);
    }

    @Deprecated
    public BitmapDrawableDecoder(Resources resources, BitmapPool bitmapPool, ResourceDecoder<DataType, Bitmap> resourceDecoder) {
        this(resources, resourceDecoder);
    }

    public BitmapDrawableDecoder(Resources resources, ResourceDecoder<DataType, Bitmap> resourceDecoder) {
        this.resources = (Resources) Preconditions.checkNotNull(resources);
        this.decoder = (ResourceDecoder) Preconditions.checkNotNull(resourceDecoder);
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public boolean handles(DataType datatype, Options options) throws IOException {
        return this.decoder.handles(datatype, options);
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public Resource<BitmapDrawable> decode(DataType datatype, int i, int i2, Options options) throws IOException {
        return LazyBitmapDrawableResource.obtain(this.resources, this.decoder.decode(datatype, i, i2, options));
    }
}
