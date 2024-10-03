package com.p017xx.commom.glide.load.engine;

import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.EncodeStrategy;

/* loaded from: classes3.dex */
public abstract class DiskCacheStrategy {
    public static final DiskCacheStrategy ALL = new DiskCacheStrategy() { // from class: com.xx.commom.glide.load.engine.DiskCacheStrategy.1
        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean decodeCachedData() {
            return true;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean decodeCachedResource() {
            return true;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean isDataCacheable(DataSource dataSource) {
            return dataSource == DataSource.REMOTE;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean isResourceCacheable(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return (dataSource == DataSource.RESOURCE_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    };
    public static final DiskCacheStrategy NONE = new DiskCacheStrategy() { // from class: com.xx.commom.glide.load.engine.DiskCacheStrategy.2
        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean decodeCachedData() {
            return false;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean decodeCachedResource() {
            return false;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean isDataCacheable(DataSource dataSource) {
            return false;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean isResourceCacheable(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return false;
        }
    };
    public static final DiskCacheStrategy DATA = new DiskCacheStrategy() { // from class: com.xx.commom.glide.load.engine.DiskCacheStrategy.3
        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean decodeCachedData() {
            return true;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean decodeCachedResource() {
            return false;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean isResourceCacheable(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return false;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean isDataCacheable(DataSource dataSource) {
            return (dataSource == DataSource.DATA_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    };
    public static final DiskCacheStrategy RESOURCE = new DiskCacheStrategy() { // from class: com.xx.commom.glide.load.engine.DiskCacheStrategy.4
        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean decodeCachedData() {
            return false;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean decodeCachedResource() {
            return true;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean isDataCacheable(DataSource dataSource) {
            return false;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean isResourceCacheable(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return (dataSource == DataSource.RESOURCE_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    };
    public static final DiskCacheStrategy AUTOMATIC = new DiskCacheStrategy() { // from class: com.xx.commom.glide.load.engine.DiskCacheStrategy.5
        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean decodeCachedData() {
            return true;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean decodeCachedResource() {
            return true;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean isDataCacheable(DataSource dataSource) {
            return dataSource == DataSource.REMOTE;
        }

        @Override // com.p017xx.commom.glide.load.engine.DiskCacheStrategy
        public boolean isResourceCacheable(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return ((z && dataSource == DataSource.DATA_DISK_CACHE) || dataSource == DataSource.LOCAL) && encodeStrategy == EncodeStrategy.TRANSFORMED;
        }
    };

    public abstract boolean decodeCachedData();

    public abstract boolean decodeCachedResource();

    public abstract boolean isDataCacheable(DataSource dataSource);

    public abstract boolean isResourceCacheable(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy);
}
