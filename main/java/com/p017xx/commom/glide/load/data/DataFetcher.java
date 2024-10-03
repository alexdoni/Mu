package com.p017xx.commom.glide.load.data;

import com.p017xx.commom.glide.Priority;
import com.p017xx.commom.glide.load.DataSource;

/* loaded from: classes3.dex */
public interface DataFetcher<T> {

    /* loaded from: classes3.dex */
    public interface DataCallback<T> {
        void onDataReady(T t);

        void onLoadFailed(Exception exc);
    }

    void cancel();

    void cleanup();

    Class<T> getDataClass();

    DataSource getDataSource();

    void loadData(Priority priority, DataCallback<? super T> dataCallback);
}
