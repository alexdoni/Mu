package com.p017xx.commom.glide.load.engine;

import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.load.data.DataFetcher;
import com.p017xx.commom.glide.load.engine.DataFetcherGenerator;
import com.p017xx.commom.glide.load.model.ModelLoader;
import java.io.File;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class DataCacheGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    private File cacheFile;
    private final List<Key> cacheKeys;

    /* renamed from: cb */
    private final DataFetcherGenerator.FetcherReadyCallback f1915cb;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int modelLoaderIndex;
    private List<ModelLoader<File, ?>> modelLoaders;
    private int sourceIdIndex;
    private Key sourceKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataCacheGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this(decodeHelper.getCacheKeys(), decodeHelper, fetcherReadyCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataCacheGenerator(List<Key> list, DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.sourceIdIndex = -1;
        this.cacheKeys = list;
        this.helper = decodeHelper;
        this.f1915cb = fetcherReadyCallback;
    }

    @Override // com.p017xx.commom.glide.load.engine.DataFetcherGenerator
    public boolean startNext() {
        while (true) {
            boolean z = false;
            if (this.modelLoaders == null || !hasNextModelLoader()) {
                int i = this.sourceIdIndex + 1;
                this.sourceIdIndex = i;
                if (i >= this.cacheKeys.size()) {
                    return false;
                }
                Key key = this.cacheKeys.get(this.sourceIdIndex);
                File file = this.helper.getDiskCache().get(new DataCacheKey(key, this.helper.getSignature()));
                this.cacheFile = file;
                if (file != null) {
                    this.sourceKey = key;
                    this.modelLoaders = this.helper.getModelLoaders(file);
                    this.modelLoaderIndex = 0;
                }
            } else {
                this.loadData = null;
                while (!z && hasNextModelLoader()) {
                    List<ModelLoader<File, ?>> list = this.modelLoaders;
                    int i2 = this.modelLoaderIndex;
                    this.modelLoaderIndex = i2 + 1;
                    this.loadData = list.get(i2).buildLoadData(this.cacheFile, this.helper.getWidth(), this.helper.getHeight(), this.helper.getOptions());
                    if (this.loadData != null && this.helper.hasLoadPath(this.loadData.fetcher.getDataClass())) {
                        this.loadData.fetcher.loadData(this.helper.getPriority(), this);
                        z = true;
                    }
                }
                return z;
            }
        }
    }

    private boolean hasNextModelLoader() {
        return this.modelLoaderIndex < this.modelLoaders.size();
    }

    @Override // com.p017xx.commom.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> loadData = this.loadData;
        if (loadData != null) {
            loadData.fetcher.cancel();
        }
    }

    @Override // com.xx.commom.glide.load.data.DataFetcher.DataCallback
    public void onDataReady(Object obj) {
        this.f1915cb.onDataFetcherReady(this.sourceKey, obj, this.loadData.fetcher, DataSource.DATA_DISK_CACHE, this.sourceKey);
    }

    @Override // com.xx.commom.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(Exception exc) {
        this.f1915cb.onDataFetcherFailed(this.sourceKey, exc, this.loadData.fetcher, DataSource.DATA_DISK_CACHE);
    }
}
