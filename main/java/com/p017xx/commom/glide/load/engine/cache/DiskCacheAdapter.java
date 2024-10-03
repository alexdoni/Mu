package com.p017xx.commom.glide.load.engine.cache;

import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.load.engine.cache.DiskCache;
import java.io.File;

/* loaded from: classes3.dex */
public class DiskCacheAdapter implements DiskCache {
    @Override // com.p017xx.commom.glide.load.engine.cache.DiskCache
    public void clear() {
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.DiskCache
    public void delete(Key key) {
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.DiskCache
    public File get(Key key) {
        return null;
    }

    @Override // com.p017xx.commom.glide.load.engine.cache.DiskCache
    public void put(Key key, DiskCache.Writer writer) {
    }

    /* loaded from: classes3.dex */
    public static final class Factory implements DiskCache.Factory {
        @Override // com.xx.commom.glide.load.engine.cache.DiskCache.Factory
        public DiskCache build() {
            return new DiskCacheAdapter();
        }
    }
}
