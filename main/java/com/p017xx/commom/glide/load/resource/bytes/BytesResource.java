package com.p017xx.commom.glide.load.resource.bytes;

import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.util.Preconditions;

/* loaded from: classes3.dex */
public class BytesResource implements Resource<byte[]> {
    private final byte[] bytes;

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public void recycle() {
    }

    public BytesResource(byte[] bArr) {
        this.bytes = (byte[]) Preconditions.checkNotNull(bArr);
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public Class<byte[]> getResourceClass() {
        return byte[].class;
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public byte[] get() {
        return this.bytes;
    }

    @Override // com.p017xx.commom.glide.load.engine.Resource
    public int getSize() {
        return this.bytes.length;
    }
}
