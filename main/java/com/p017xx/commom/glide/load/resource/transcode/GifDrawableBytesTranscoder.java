package com.p017xx.commom.glide.load.resource.transcode;

import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.resource.bytes.BytesResource;
import com.p017xx.commom.glide.load.resource.gif.GifDrawable;
import com.p017xx.commom.glide.util.ByteBufferUtil;

/* loaded from: classes3.dex */
public class GifDrawableBytesTranscoder implements ResourceTranscoder<GifDrawable, byte[]> {
    @Override // com.p017xx.commom.glide.load.resource.transcode.ResourceTranscoder
    public Resource<byte[]> transcode(Resource<GifDrawable> resource, Options options) {
        return new BytesResource(ByteBufferUtil.toBytes(resource.get().getBuffer()));
    }
}
