package com.p017xx.commom.glide.load.engine;

import android.util.Log;
import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.Encoder;
import com.p017xx.commom.glide.load.Key;
import com.p017xx.commom.glide.load.data.DataFetcher;
import com.p017xx.commom.glide.load.engine.DataFetcherGenerator;
import com.p017xx.commom.glide.load.model.ModelLoader;
import com.p017xx.commom.glide.util.LogTime;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class SourceGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object>, DataFetcherGenerator.FetcherReadyCallback {
    private static final String TAG = "SourceGenerator";

    /* renamed from: cb */
    private final DataFetcherGenerator.FetcherReadyCallback f1921cb;
    private Object dataToCache;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int loadDataListIndex;
    private DataCacheKey originalKey;
    private DataCacheGenerator sourceCacheGenerator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SourceGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.helper = decodeHelper;
        this.f1921cb = fetcherReadyCallback;
    }

    @Override // com.p017xx.commom.glide.load.engine.DataFetcherGenerator
    public boolean startNext() {
        Object obj = this.dataToCache;
        if (obj != null) {
            this.dataToCache = null;
            cacheData(obj);
        }
        DataCacheGenerator dataCacheGenerator = this.sourceCacheGenerator;
        if (dataCacheGenerator != null && dataCacheGenerator.startNext()) {
            return true;
        }
        this.sourceCacheGenerator = null;
        this.loadData = null;
        boolean z = false;
        while (!z && hasNextModelLoader()) {
            List<ModelLoader.LoadData<?>> loadData = this.helper.getLoadData();
            int i = this.loadDataListIndex;
            this.loadDataListIndex = i + 1;
            this.loadData = loadData.get(i);
            if (this.loadData != null && (this.helper.getDiskCacheStrategy().isDataCacheable(this.loadData.fetcher.getDataSource()) || this.helper.hasLoadPath(this.loadData.fetcher.getDataClass()))) {
                this.loadData.fetcher.loadData(this.helper.getPriority(), this);
                z = true;
            }
        }
        return z;
    }

    private boolean hasNextModelLoader() {
        return this.loadDataListIndex < this.helper.getLoadData().size();
    }

    private void cacheData(Object obj) {
        long logTime = LogTime.getLogTime();
        try {
            Encoder<X> sourceEncoder = this.helper.getSourceEncoder(obj);
            DataCacheWriter dataCacheWriter = new DataCacheWriter(sourceEncoder, obj, this.helper.getOptions());
            this.originalKey = new DataCacheKey(this.loadData.sourceKey, this.helper.getSignature());
            this.helper.getDiskCache().put(this.originalKey, dataCacheWriter);
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Finished encoding source to cache, key: " + this.originalKey + ", data: " + obj + ", encoder: " + sourceEncoder + ", duration: " + LogTime.getElapsedMillis(logTime));
            }
            this.loadData.fetcher.cleanup();
            this.sourceCacheGenerator = new DataCacheGenerator(Collections.singletonList(this.loadData.sourceKey), this.helper, this);
        } catch (Throwable th) {
            this.loadData.fetcher.cleanup();
            throw th;
        }
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
        DiskCacheStrategy diskCacheStrategy = this.helper.getDiskCacheStrategy();
        if (obj != null && diskCacheStrategy.isDataCacheable(this.loadData.fetcher.getDataSource())) {
            this.dataToCache = obj;
            this.f1921cb.reschedule();
        } else {
            this.f1921cb.onDataFetcherReady(this.loadData.sourceKey, obj, this.loadData.fetcher, this.loadData.fetcher.getDataSource(), this.originalKey);
        }
    }

    @Override // com.xx.commom.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(Exception exc) {
        this.f1921cb.onDataFetcherFailed(this.originalKey, exc, this.loadData.fetcher, this.loadData.fetcher.getDataSource());
    }

    @Override // com.xx.commom.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void reschedule() {
        throw new UnsupportedOperationException();
    }

    @Override // com.xx.commom.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.f1921cb.onDataFetcherReady(key, obj, dataFetcher, this.loadData.fetcher.getDataSource(), key);
    }

    @Override // com.xx.commom.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        this.f1921cb.onDataFetcherFailed(key, exc, dataFetcher, this.loadData.fetcher.getDataSource());
    }
}
