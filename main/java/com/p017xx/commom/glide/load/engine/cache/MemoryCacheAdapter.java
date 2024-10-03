package com.p017xx.commom.glide.load.engine.cache;

import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.cache.MemoryCache;

/* loaded from: classes3.dex */
public class MemoryCacheAdapter implements MemoryCache {
    private MemoryCache.ResourceRemovedListener listener;

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public void clearMemory() {
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public long getCurrentSize() {
        return 0L;
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public long getMaxSize() {
        return 0L;
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public Resource<?> remove(Key key) {
        return null;
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public void setSizeMultiplier(float f) {
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public void trimMemory(int i) {
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public Resource<?> put(Key key, Resource<?> resource) {
        if (resource == null) {
            return null;
        }
        this.listener.onResourceRemoved(resource);
        return null;
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public void setResourceRemovedListener(MemoryCache.ResourceRemovedListener resourceRemovedListener) {
        this.listener = resourceRemovedListener;
    }
}
