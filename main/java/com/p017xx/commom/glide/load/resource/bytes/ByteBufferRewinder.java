package com.p017xx.commom.glide.load.resource.bytes;

import com.p017xx.commom.glide.load.data.DataRewinder;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class ByteBufferRewinder implements DataRewinder<ByteBuffer> {
    private final ByteBuffer buffer;

    @Override // com.p017xx.commom.glide.load.data.DataRewinder
    public void cleanup() {
    }

    public ByteBufferRewinder(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
    }

    @Override // com.p017xx.commom.glide.load.data.DataRewinder
    public ByteBuffer rewindAndGet() {
        this.buffer.position(0);
        return this.buffer;
    }

    /* loaded from: classes3.dex */
    public static class Factory implements DataRewinder.Factory<ByteBuffer> {
        @Override // com.xx.commom.glide.load.data.DataRewinder.Factory
        public DataRewinder<ByteBuffer> build(ByteBuffer byteBuffer) {
            return new ByteBufferRewinder(byteBuffer);
        }

        @Override // com.xx.commom.glide.load.data.DataRewinder.Factory
        public Class<ByteBuffer> getDataClass() {
            return ByteBuffer.class;
        }
    }
}
