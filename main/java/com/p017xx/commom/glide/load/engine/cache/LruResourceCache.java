package com.p017xx.commom.glide.load.engine.cache;

import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.load.engine.Resource;
import com.p017xx.commom.glide.load.engine.cache.MemoryCache;
import com.p017xx.commom.glide.util.LruCache;

/* loaded from: classes3.dex */
public class LruResourceCache extends LruCache<Key, Resource<?>> implements MemoryCache {
    private MemoryCache.ResourceRemovedListener listener;

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public /* bridge */ /* synthetic */ Resource put(Key key, Resource resource) {
        return (Resource) super.put((LruResourceCache) key, (Key) resource);
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public /* bridge */ /* synthetic */ Resource remove(Key key) {
        return (Resource) super.remove((LruResourceCache) key);
    }

    public LruResourceCache(long j) {
        super(j);
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public void setResourceRemovedListener(MemoryCache.ResourceRemovedListener resourceRemovedListener) {
        this.listener = resourceRemovedListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p017xx.commom.glide.util.LruCache
    public void onItemEvicted(Key key, Resource<?> resource) {
        MemoryCache.ResourceRemovedListener resourceRemovedListener = this.listener;
        if (resourceRemovedListener == null || resource == null) {
            return;
        }
        resourceRemovedListener.onResourceRemoved(resource);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p017xx.commom.glide.util.LruCache
    public int getSize(Resource<?> resource) {
        if (resource == null) {
            return super.getSize((LruResourceCache) null);
        }
        return resource.getSize();
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.MemoryCache
    public void trimMemory(int i) {
        if (i >= 40) {
            clearMemory();
        } else if (i >= 20 || i == 15) {
            trimToSize(getMaxSize() / 2);
        }
    }
}
