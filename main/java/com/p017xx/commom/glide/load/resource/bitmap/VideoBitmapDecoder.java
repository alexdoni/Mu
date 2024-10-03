package com.p017xx.commom.glide.load.resource.bitmap;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.p017xx.commom.glide.Glide;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.load.resource.bitmap.VideoDecoder;

@Deprecated
/* loaded from: classes3.dex */
public class VideoBitmapDecoder extends VideoDecoder<ParcelFileDescriptor> {
    public VideoBitmapDecoder(Context context) {
        this(Glide.get(context).getBitmapPool());
    }

    public VideoBitmapDecoder(BitmapPool bitmapPool) {
        super(bitmapPool, new VideoDecoder.ParcelFileDescriptorInitializer());
    }
}
