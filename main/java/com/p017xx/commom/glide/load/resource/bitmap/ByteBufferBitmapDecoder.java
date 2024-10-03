package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceDecoder;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.util.ByteBufferUtil;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class ByteBufferBitmapDecoder implements ResourceDecoder<ByteBuffer, Bitmap> {
    private final Downsampler downsampler;

    public ByteBufferBitmapDecoder(Downsampler downsampler) {
        this.downsampler = downsampler;
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public boolean handles(ByteBuffer byteBuffer, Options options) {
        return this.downsampler.handles(byteBuffer);
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(ByteBuffer byteBuffer, int i, int i2, Options options) throws IOException {
        return this.downsampler.decode(ByteBufferUtil.toStream(byteBuffer), i, i2, options);
    }
}
