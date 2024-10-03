package com.p017xx.commom.glide.load.data;

import com.p017xx.commom.glide.load.data.DataRewinder;
import com.p017xx.commom.glide.load.engine.bitmap_recycle.ArrayPool;
import com.p017xx.commom.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public final class InputStreamRewinder implements DataRewinder<InputStream> {
    private static final int MARK_LIMIT = 5242880;
    private final RecyclableBufferedInputStream bufferedStream;

    InputStreamRewinder(InputStream inputStream, ArrayPool arrayPool) {
        RecyclableBufferedInputStream recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        this.bufferedStream = recyclableBufferedInputStream;
        recyclableBufferedInputStream.mark(MARK_LIMIT);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.p017xx.commom.glide.load.data.DataRewinder
    public InputStream rewindAndGet() throws IOException {
        this.bufferedStream.reset();
        return this.bufferedStream;
    }

    @Override // com.p017xx.commom.glide.load.data.DataRewinder
    public void cleanup() {
        this.bufferedStream.release();
    }

    /* loaded from: classes3.dex */
    public static final class Factory implements DataRewinder.Factory<InputStream> {
        private final ArrayPool byteArrayPool;

        public Factory(ArrayPool arrayPool) {
            this.byteArrayPool = arrayPool;
        }

        @Override // com.xx.commom.glide.load.data.DataRewinder.Factory
        public DataRewinder<InputStream> build(InputStream inputStream) {
            return new InputStreamRewinder(inputStream, this.byteArrayPool);
        }

        @Override // com.xx.commom.glide.load.data.DataRewinder.Factory
        public Class<InputStream> getDataClass() {
            return InputStream.class;
        }
    }
}
