package com.p017xx.commom.glide.load.resource.transcode;

import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.engine.Resource;

/* loaded from: classes3.dex */
public class UnitTranscoder<Z> implements ResourceTranscoder<Z, Z> {
    private static final UnitTranscoder<?> UNIT_TRANSCODER = new UnitTranscoder<>();

    @Override // com.p017xx.commom.glide.load.resource.transcode.ResourceTranscoder
    public Resource<Z> transcode(Resource<Z> resource, Options options) {
        return resource;
    }

    public static <Z> ResourceTranscoder<Z, Z> get() {
        return UNIT_TRANSCODER;
    }
}
