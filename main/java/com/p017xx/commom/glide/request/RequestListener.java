package com.p017xx.commom.glide.request;

import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.engine.GlideException;
import com.p017xx.commom.glide.request.target.Target;

/* loaded from: classes3.dex */
public interface RequestListener<R> {
    boolean onLoadFailed(GlideException glideException, Object obj, Target<R> target, boolean z);

    boolean onResourceReady(R r, Object obj, Target<R> target, DataSource dataSource, boolean z);
}
