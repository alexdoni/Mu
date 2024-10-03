package com.p017xx.commom.glide.load.engine.cache;

import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.load.engine.Resource;

/* loaded from: classes3.dex */
public interface MemoryCache {

    /* loaded from: classes3.dex */
    public interface ResourceRemovedListener {
        void onResourceRemoved(Resource<?> resource);
    }

    void clearMemory();

    long getCurrentSize();

    long getMaxSize();

    Resource<?> put(Key key, Resource<?> resource);

    Resource<?> remove(Key key);

    void setResourceRemovedListener(ResourceRemovedListener resourceRemovedListener);

    void setSizeMultiplier(float f);

    void trimMemory(int i);
}
