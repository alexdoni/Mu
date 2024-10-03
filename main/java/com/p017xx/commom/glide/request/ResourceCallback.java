package com.p017xx.commom.glide.request;

import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.engine.GlideException;
import com.p017xx.commom.glide.load.engine.Resource;

/* loaded from: classes3.dex */
public interface ResourceCallback {
    void onLoadFailed(GlideException glideException);

    void onResourceReady(Resource<?> resource, DataSource dataSource);
}
