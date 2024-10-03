package com.p017xx.commom.glide.load.engine;

import com.p017xx.commom.glide.load.Key;

/* loaded from: classes3.dex */
interface EngineJobListener {
    void onEngineJobCancelled(EngineJob<?> engineJob, Key key);

    void onEngineJobComplete(EngineJob<?> engineJob, Key key, EngineResource<?> engineResource);
}
