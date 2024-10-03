package com.p017xx.commom.glide.load.model;

import android.util.Log;
import com.p017xx.commom.glide.Priority;
import com.p017xx.commom.glide.load.DataSource;
import com.p017xx.commom.glide.load.Options;
import com.p017xx.commom.glide.load.data.DataFetcher;
import com.p017xx.commom.glide.load.model.ModelLoader;
import com.p017xx.commom.glide.signature.ObjectKey;
import com.p017xx.commom.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class ByteBufferFileLoader implements ModelLoader<File, ByteBuffer> {
    private static final String TAG = "ByteBufferFileLoader";

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public boolean handles(File file) {
        return true;
    }

    @Override // com.p017xx.commom.glide.load.model.ModelLoader
    public ModelLoader.LoadData<ByteBuffer> buildLoadData(File file, int i, int i2, Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(file), new ByteBufferFetcher(file));
    }

    /* loaded from: classes3.dex */
    public static class Factory implements ModelLoaderFactory<File, ByteBuffer> {
        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }

        @Override // com.p017xx.commom.glide.load.model.ModelLoaderFactory
        public ModelLoader<File, ByteBuffer> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ByteBufferFileLoader();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ByteBufferFetcher implements DataFetcher<ByteBuffer> {
        private final File file;

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public void cancel() {
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public void cleanup() {
        }

        ByteBufferFetcher(File file) {
            this.file = file;
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public void loadData(Priority priority, DataFetcher.DataCallback<? super ByteBuffer> dataCallback) {
            try {
                dataCallback.onDataReady(ByteBufferUtil.fromFile(this.file));
            } catch (IOException e) {
                if (Log.isLoggable(ByteBufferFileLoader.TAG, 3)) {
                    Log.d(ByteBufferFileLoader.TAG, "Failed to obtain ByteBuffer for file", e);
                }
                dataCallback.onLoadFailed(e);
            }
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public Class<ByteBuffer> getDataClass() {
            return ByteBuffer.class;
        }

        @Override // com.p017xx.commom.glide.load.data.DataFetcher
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }
}
