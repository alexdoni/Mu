package com.p017xx.commom.glide.request.transition;

import com.p017xx.commom.glide.load.DataSource;

/* loaded from: classes3.dex */
public interface TransitionFactory<R> {
    Transition<R> build(DataSource dataSource, boolean z);
}
