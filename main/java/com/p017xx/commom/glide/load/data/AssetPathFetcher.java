package com.p017xx.commom.glide.load.data;

import android.content.res.AssetManager;
import android.util.Log;
import com.p017xx.commom.glide.Priority;
import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.data.DataFetcher;
import java.io.IOException;

/* loaded from: classes3.dex */
public abstract class AssetPathFetcher<T> implements DataFetcher<T> {
    private static final String TAG = "AssetPathFetcher";
    private final AssetManager assetManager;
    private final String assetPath;
    private T data;

    @Override // com.p017xx.commom.glide.load.data.DataFetcher
    public void cancel() {
    }

    protected abstract void close(T t) throws IOException;

    protected abstract T loadResource(AssetManager assetManager, String str) throws IOException;

    public AssetPathFetcher(AssetManager assetManager, String str) {
        this.assetManager = assetManager;
        this.assetPath = str;
    }

    @Override // com.p017xx.commom.glide.load.data.DataFetcher
    public void loadData(Priority priority, DataFetcher.DataCallback<? super T> dataCallback) {
        try {
            T loadResource = loadResource(this.assetManager, this.assetPath);
            this.data = loadResource;
            dataCallback.onDataReady(loadResource);
        } catch (IOException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to load data from asset manager", e);
            }
            dataCallback.onLoadFailed(e);
        }
    }

    @Override // com.p017xx.commom.glide.load.data.DataFetcher
    public void cleanup() {
        T t = this.data;
        if (t == null) {
            return;
        }
        try {
            close(t);
        } catch (IOException unused) {
        }
    }

    @Override // com.p017xx.commom.glide.load.data.DataFetcher
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
