package com.p017xx.commom.glide.load.resource.transcode;

import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.engine.Resource;

/* loaded from: classes3.dex */
public interface ResourceTranscoder<Z, R> {
    Resource<R> transcode(Resource<Z> resource, Options options);
}
