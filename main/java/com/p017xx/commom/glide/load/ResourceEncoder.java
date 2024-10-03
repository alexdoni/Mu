package com.p017xx.commom.glide.load;

import com.p017xx.commom.glide.load.engine.Resource;

/* loaded from: classes3.dex */
public interface ResourceEncoder<T> extends Encoder<Resource<T>> {
    EncodeStrategy getEncodeStrategy(Options options);
}
