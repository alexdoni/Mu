package com.p017xx.commom.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.ResourceDecoder;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayPool;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.BitmapPool;
import com.p017xx.commom.glide.load.resource.bitmap.Downsampler;
import com.p017xx.commom.glide.util.ExceptionCatchingInputStream;
import com.p017xx.commom.glide.util.MarkEnforcingInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class StreamBitmapDecoder implements ResourceDecoder<InputStream, Bitmap> {
    private final ArrayPool byteArrayPool;
    private final Downsampler downsampler;

    public StreamBitmapDecoder(Downsampler downsampler, ArrayPool arrayPool) {
        this.downsampler = downsampler;
        this.byteArrayPool = arrayPool;
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public boolean handles(InputStream inputStream, Options options) {
        return this.downsampler.handles(inputStream);
    }

    @Override // com.p017xx.commom.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options) throws IOException {
        boolean z;
        RecyclableBufferedInputStream recyclableBufferedInputStream;
        if (inputStream instanceof RecyclableBufferedInputStream) {
            recyclableBufferedInputStream = (RecyclableBufferedInputStream) inputStream;
            z = false;
        } else {
            z = true;
            recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, this.byteArrayPool);
        }
        ExceptionCatchingInputStream obtain = ExceptionCatchingInputStream.obtain(recyclableBufferedInputStream);
        try {
            return this.downsampler.decode(new MarkEnforcingInputStream(obtain), i, i2, options, new UntrustedCallbacks(recyclableBufferedInputStream, obtain));
        } finally {
            obtain.release();
            if (z) {
                recyclableBufferedInputStream.release();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class UntrustedCallbacks implements Downsampler.DecodeCallbacks {
        private final RecyclableBufferedInputStream bufferedStream;
        private final ExceptionCatchingInputStream exceptionStream;

        UntrustedCallbacks(RecyclableBufferedInputStream recyclableBufferedInputStream, ExceptionCatchingInputStream exceptionCatchingInputStream) {
            this.bufferedStream = recyclableBufferedInputStream;
            this.exceptionStream = exceptionCatchingInputStream;
        }

        @Override // com.xx.commom.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onObtainBounds() {
            this.bufferedStream.fixMarkLimit();
        }

        @Override // com.xx.commom.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException {
            IOException exception = this.exceptionStream.getException();
            if (exception != null) {
                if (bitmap != null) {
                    bitmapPool.put(bitmap);
                    throw exception;
                }
                throw exception;
            }
        }
    }
}
